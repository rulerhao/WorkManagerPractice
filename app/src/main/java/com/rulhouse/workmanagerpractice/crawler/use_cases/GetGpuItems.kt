package com.rulhouse.workmanagerpractice.crawler.use_cases

import com.rulhouse.workmanagerpractice.crawler.repository.CrawlerRepository
import com.rulhouse.workmanagerpractice.data.GpuProduct

class GetGpuItems(
    private val repository: CrawlerRepository
) {
    suspend operator fun invoke(): List<GpuProduct>? {
        return repository.getGpuItems()
    }
}