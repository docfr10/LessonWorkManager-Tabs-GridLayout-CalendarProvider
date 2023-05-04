package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider

import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import android.text.format.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.TimeZone

fun getCalendarEvents(context: Context): List<Event> {
    val events = mutableListOf<Event>()
    val projection = arrayOf(
        CalendarContract.Events.TITLE,
        CalendarContract.Events.DESCRIPTION,
        CalendarContract.Events.DTSTART
    )
    val selection = "${CalendarContract.Events.DTSTART} >= ?"
    val selectionArgs = arrayOf(System.currentTimeMillis().toString())
    val sortOrder = "${CalendarContract.Events.DTSTART} ASC"
    val uri = CalendarContract.Events.CONTENT_URI
    context.contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
        ?.use { cursor ->
            val titleIndex = cursor.getColumnIndex(CalendarContract.Events.TITLE)
            val descriptionIndex = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION)
            val dtStartIndex = cursor.getColumnIndex(CalendarContract.Events.DTSTART)
            while (cursor.moveToNext()) {
                val title = cursor.getString(titleIndex)
                val description = cursor.getString(descriptionIndex)
                val dtStart = cursor.getLong(dtStartIndex)
                events.add(Event(title, description, dtStart))
            }
        }
    return events
}

@Composable
fun CalendarEventsScreen(context: Context) {
    val repository = remember { CalendarEventsRepository(context) }
    val events by repository.calendarEvents.collectAsState(emptyList())

    LazyColumn {
        item {
            Button(onClick = {
                repository.addCalendarEvent(
                    "New Event", "Event Description"
                )
            }) {
                Text(text = "Add")
            }
        }
        items(events) { event ->
            EventItem(event)
        }
    }
}

@Composable
fun EventItem(event: Event) {
    Column {
        Text(text = event.title, fontWeight = FontWeight.Bold)
        Text(text = event.description, fontWeight = FontWeight.Normal)
        Text(text = DateFormat.format("dd MMMM yyyy, HH:mm", event.dtStart).toString())
    }
}

class CalendarEventsRepository(private val context: Context) {
    val calendarEvents: Flow<List<Event>> = flow {
        while (true) {
            val events = getCalendarEvents(context)
            emit(events)
            delay(1000) // обновляйте список каждую секунду (можете настроить на другой интервал)
        }
    }

    fun addCalendarEvent(eventTitle: String, eventDescription: String) {
        val cal = Calendar.getInstance()
        val startTime = cal.timeInMillis + 60 * 60 * 1000 // время начала события через час
        val endTime = startTime + 60 * 60 * 1000 // время окончания события через час
        val values = ContentValues().apply {
            put(CalendarContract.Events.TITLE, eventTitle)
            put(CalendarContract.Events.DESCRIPTION, eventDescription)
            put(CalendarContract.Events.DTSTART, startTime)
            put(CalendarContract.Events.DTEND, endTime)
            put(CalendarContract.Events.CALENDAR_ID, 1) // Идентификатор календаря
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
    }
}

data class Event(
    val title: String,
    val description: String,
    val dtStart: Long,
)