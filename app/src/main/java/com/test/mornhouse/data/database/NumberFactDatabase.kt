package com.test.mornhouse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.mornhouse.data.model.NumberFactData

@Database(entities = [NumberFactData::class], version = 1, exportSchema = false)
abstract class NumberFactDatabase : RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}
