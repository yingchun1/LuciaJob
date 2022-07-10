package com.example.luciadailyjob

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class LuciaJobRepository(private val jobDAO: JobDAO) {

    val allJobs: Flow<List<LuciaJob>> = jobDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(luciaJob: LuciaJob) {
        jobDAO.insert(luciaJob)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(luciaJob: LuciaJob) {
        jobDAO.update(luciaJob)
    }
}