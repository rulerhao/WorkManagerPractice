package com.rulhouse.workmanagerpractice.worker.coroutine_work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases

class CoroutineWorkManagerFactory(
    private val crawlerUseCases: CrawlerUseCases
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return MyWorker(appContext, workerParameters, crawlerUseCases)
    }
}