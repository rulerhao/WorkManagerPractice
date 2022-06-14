package com.rulhouse.workmanagerpractice.crawler.impl

import com.rulhouse.workmanagerpractice.crawler.repository.CrawlerRepository
import com.rulhouse.workmanagerpractice.crawler.use_cases.WebCrawler
import com.rulhouse.workmanagerpractice.data.GpuProduct

class CrawlerImpl (
) : CrawlerRepository {

    override suspend fun getGpuItems(): List<GpuProduct>? {
        return WebCrawler.test()
    }
}