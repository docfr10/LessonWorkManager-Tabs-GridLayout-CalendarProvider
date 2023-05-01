package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lesson_workmanager_tabs_gridlayout_calendarprovider.ui.theme.LessonWorkManagerTabsGridLayoutCalendarProviderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LessonWorkManagerTabsGridLayoutCalendarProviderTheme {
                GridLayout()
            }
        }
    }
}