package com.rulhouse.workmanagerpractice

import android.content.Context
import android.util.Log
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.workmanagerpractice.worker.coroutine_work.CoroutineWorkManagerFactory
import com.rulhouse.workmanagerpractice.worker.coroutine_work.MyWorker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
//@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class TestGpuProductsWorker {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var worker : MyWorker

    private lateinit var context: Context

    @Inject
    lateinit var crawlerUseCases: CrawlerUseCases

    @Before
    fun setup() {
        hiltRule.inject()

        context = InstrumentationRegistry.getInstrumentation().targetContext

        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun testSleepWorker() {
        val worker = TestListenableWorkerBuilder<MyWorker>(context)
            .setWorkerFactory(CoroutineWorkManagerFactory(crawlerUseCases))
            .build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result, `is`(ListenableWorker.Result.success()))
        }
    }
}