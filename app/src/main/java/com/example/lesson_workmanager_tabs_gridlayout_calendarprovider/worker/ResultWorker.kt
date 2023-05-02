package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class ResultWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val resultData = workDataOf("result_key" to "Hello from ResultWorker!")
        return Result.success(resultData)
    }
}