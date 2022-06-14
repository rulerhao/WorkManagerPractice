package com.rulhouse.workmanagerpractice.crawler.repository

import com.rulhouse.workmanagerpractice.data.GpuProduct

interface CrawlerRepository {
    suspend fun getGpuItems(): List<GpuProduct>?
}