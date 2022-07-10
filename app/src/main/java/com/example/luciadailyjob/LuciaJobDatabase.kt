package com.example.luciadailyjob

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = arrayOf(LuciaJob::class), version = 1, exportSchema = false)
abstract class LuciaJobDatabase : RoomDatabase(){
    abstract fun jobDAO(): JobDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LuciaJobDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LuciaJobDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LuciaJobDatabase::class.java,
                    "lucia_job_database"
                ).addCallback(LuciaJobDatabaseCallback(scope = scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


    private class LuciaJobDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.jobDAO())
                }
            }
        }

        suspend fun populateDatabase(jobDAO: JobDAO) {
            // Delete all content here.
            jobDAO.deleteAll()

            var job = LuciaJob(Date(2022, 7,1), 1, FinishStatus.NOT_DONE)
            jobDAO.insert(job = job)
        }
    }
}
