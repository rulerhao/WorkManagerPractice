package com.rulhouse.workmanagerpractice

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MyWorker @Inject constructor(
    context: Context,
    params: WorkerParameters,
    private val crawlerUseCases: CrawlerUseCases
) : Worker(context, params){

    override fun doWork(): Result {

        val myScope = object:CoroutineScope{
            override val coroutineContext: CoroutineContext
                get() = Job()
        }

        myScope.launch {
            Log.d("TestWorker", "GpuItems = ${crawlerUseCases.getGpuItems()}")
        }

        val outputData = workDataOf("OutputDataKey" to "OutputDataValue")

        return Result.success(outputData)
    }

    @SuppressLint("RestrictedApi")
    fun workDataOf(vararg pairs: Pair<String, Any?>): Data {
        val dataBuilder = Data.Builder()
        for (pair in pairs) {
            dataBuilder.put(pair.first, pair.second)
        }
        return dataBuilder.build()
    }
}