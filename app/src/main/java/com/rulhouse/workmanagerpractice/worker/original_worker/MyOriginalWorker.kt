package com.rulhouse.workmanagerpractice.worker.original_worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MyOriginalWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
): Worker(appContext, workerParams){

    override fun doWork(): Result {

        return Result.success()
    }
}