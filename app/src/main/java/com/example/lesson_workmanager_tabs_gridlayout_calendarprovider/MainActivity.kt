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
                // GridLayout()
                // TabLayout()

                // One time work request
                /*
                val simpleWorkRequest = OneTimeWorkRequestBuilder<SimpleWorker>().build()
                WorkManager.getInstance(applicationContext).enqueue(simpleWorkRequest)
                // Periodic work request
                val periodicWorkRequest =
                    PeriodicWorkRequestBuilder<PeriodicWorker>(15, TimeUnit.MINUTES).build()
                WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
                // Constrained work request
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresCharging(true)
                    .build()
                val constrainedWorkRequest = OneTimeWorkRequestBuilder<ConstrainedWorker>()
                    .setConstraints(constraints)
                    .build()
                WorkManager.getInstance(applicationContext).enqueue(constrainedWorkRequest)
                // Result work request
                val resultWorkRequest = OneTimeWorkRequestBuilder<ResultWorker>().build()
                WorkManager.getInstance(applicationContext)
                    .getWorkInfoByIdLiveData(resultWorkRequest.id)
                    .observe(this) { workInfo ->
                        if (workInfo?.state == WorkInfo.State.SUCCEEDED) {
                            val result = workInfo.outputData.getString("result_key")
                            Log.d("ResultWorker", "Result: $result")
                        }
                    }
                WorkManager.getInstance(applicationContext).enqueue(resultWorkRequest)
                 */
                CalendarEventsScreen(applicationContext)
            }
        }
    }
}