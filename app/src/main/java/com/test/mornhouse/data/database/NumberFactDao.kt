package com.test.mornhouse.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.mornhouse.data.model.NumberFactData
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(numberFact: NumberFactData)

    @Query("SELECT * FROM number_facts ORDER BY id DESC")
    fun getAllFacts(): Flow<List<NumberFactData>>
}
