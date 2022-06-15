package com.rulhouse.workmanagerpractice

import android.content.Context
import android.util.Log
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.*
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.rulhouse.workmanagerpractice.worker.original_worker.MyOriginalWorker
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class OriginalWorkTest {

    private lateinit var context: Context
    private lateinit var executor: Executor

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        executor = Executors.newSingleThreadExecutor()

        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun testPeriodicWork() {
        // Define input data
        val input = workDataOf("OutputDataKey" to "OutputDataValue")

        // Create request
        val request = PeriodicWorkRequestBuilder<MyOriginalWorker>(15, TimeUnit.MINUTES)
            .setInputData(input)
            .build()

        val workManager = WorkManager.getInstance(context)
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)
        // Enqueue and wait for result.
        workManager.enqueue(request).result.get()
        // Tells the testing framework the period delay is met
        testDriver?.setPeriodDelayMet(request.id)
        // Get WorkInfo and outputData
        val workInfo = workManager.getWorkInfoById(request.id).get()

        Log.d("TestTest", "Before Assert")
        // Assert
        assertThat(workInfo.state, `is`(WorkInfo.State.ENQUEUED))
    }

    @Test
    fun testDoWorker() {
        val worker = TestWorkerBuilder<MyOriginalWorker>(
            context = context,
            executor = executor
        ).build()

        val result = worker.doWork()
        assertThat(result, `is`(ListenableWorker.Result.success()))
    }
}