package com.rulhouse.workmanagerpractice.work_manager.di

import android.content.Context
import com.rulhouse.workmanagerpractice.work_manager.factory.WorkManagerFactory
import com.rulhouse.workmanagerpractice.work_manager.impl.WorkManagerImpl
import com.rulhouse.workmanagerpractice.work_manager.repository.WorkManagerRepository
import com.rulhouse.workmanagerpractice.work_manager.use_cases.CancelPeriodicWork
import com.rulhouse.workmanagerpractice.work_manager.use_cases.SetPeriodicWork
import com.rulhouse.workmanagerpractice.work_manager.use_cases.WorkManagerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {

    @Singleton
    @Provides
    fun provideWorkManagerFactory(): WorkManagerFactory {
        return WorkManagerFactory()
    }

    @Provides
    @Singleton
    fun provideWorkManagerRepository(@ApplicationContext context: Context, workManagerFactory: WorkManagerFactory): WorkManagerRepository {
        return WorkManagerImpl(context, workManagerFactory)
    }

    @Provides
    @Singleton
    fun provideWorkManagerUseCases(repository: WorkManagerRepository): WorkManagerUseCases {
        return WorkManagerUseCases(
            setPeriodicWork = SetPeriodicWork(repository),
            cancelPeriodicWork = CancelPeriodicWork(repository)
        )
    }

}