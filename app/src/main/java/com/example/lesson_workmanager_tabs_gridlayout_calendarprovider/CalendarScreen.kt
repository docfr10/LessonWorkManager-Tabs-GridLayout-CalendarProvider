package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider

import android.content.Context
import android.provider.CalendarContract
import android.text.format.DateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

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
    val events = getCalendarEvents(context)
    LazyColumn {
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

data class Event(
    val title: String,
    val description: String,
    val dtStart: Long,
)