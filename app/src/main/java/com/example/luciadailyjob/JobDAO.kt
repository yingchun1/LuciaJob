package com.example.luciadailyjob

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDAO {
    @Query("SELECT * FROM lucia_job_table ORDER BY date ASC, item_id ASC")
    fun getAll(): Flow<List<LuciaJob>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg jobs: LuciaJob)

    @Insert
    suspend fun insert(job: LuciaJob)

    @Delete
    fun delete(job: LuciaJob)

    @Update
    fun update(job: LuciaJob)

    @Query("DELETE FROM lucia_job_table")
    suspend fun deleteAll()
}