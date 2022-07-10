package com.example.luciadailyjob

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import org.jetbrains.annotations.NotNull
import java.util.*


@Entity(tableName = "lucia_job_table", primaryKeys = ["date", "item_id"])
@TypeConverters(DateConverter::class)
class LuciaJob(
    @ColumnInfo(name = "date")
    @NotNull
    var date: Date,

    @ColumnInfo(name = "item_id")
    @NotNull
    var itemId: Int,

    @ColumnInfo(name = "item_status")
    var itemStatus: Int
)