package com.rulhouse.workmanagerpractice.work_manager.impl

import android.content.Context
import androidx.work.*
import com.rulhouse.workmanagerpractice.worker.coroutine_work.MyWorker
import com.rulhouse.workmanagerpractice.work_manager.factory.WorkManagerFactory
import com.rulhouse.workmanagerpractice.work_manager.repository.WorkManagerRepository
import java.util.concurrent.TimeUnit

class WorkManagerImpl(
    private val context: Context,
    private val workManagerFactory: WorkManagerFactory
): WorkManagerRepository {

    override fun setWork(workerClass: Class<out ListenableWorker>) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest.Builder(workerClass, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            workManagerFactory.workManagerUniqueName,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    override fun setPeriodicWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            workManagerFactory.workManagerUniqueName,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    override fun cancelPeriodicWork() {
        WorkManager.getInstance(context).cancelUniqueWork(
            workManagerFactory.workManagerUniqueName
        )
    }

}