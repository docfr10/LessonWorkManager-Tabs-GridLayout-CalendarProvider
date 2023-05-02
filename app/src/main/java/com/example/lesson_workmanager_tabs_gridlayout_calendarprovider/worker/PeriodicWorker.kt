package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("PeriodicWorker", "Hello from PeriodicWorker!")
        return Result.success()
    }
}

