package com.rulhouse.workmanagerpractice.crawler.di

import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.workmanagerpractice.crawler.use_cases.GetGpuItems
import com.rulhouse.workmanagerpractice.crawler.impl.CrawlerImpl
import com.rulhouse.workmanagerpractice.crawler.repository.CrawlerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CrawlerModule {
    @Provides
    @Singleton
    fun provideMeasurementRepository(): CrawlerRepository {
        return CrawlerImpl()
    }

    @Provides
    @Singleton
    fun provideMeasurementUseCases(repository: CrawlerRepository): CrawlerUseCases {
        return CrawlerUseCases(
            getGpuItems = GetGpuItems(repository),
        )
    }
}