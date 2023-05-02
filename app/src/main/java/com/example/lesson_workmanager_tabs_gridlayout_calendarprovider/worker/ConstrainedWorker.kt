package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ConstrainedWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("ConstrainedWorker", "Hello from ConstrainedWorker!")
        return Result.success()
    }
}