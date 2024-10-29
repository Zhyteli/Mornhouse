package com.test.mornhouse.data.database

import com.test.mornhouse.data.model.NumberFactData
import kotlinx.coroutines.flow.Flow

interface NumberFactLocalDataSource {
    suspend fun saveFact(numberFact: NumberFactData)
    fun getAllFacts(): Flow<List<NumberFactData>>
}
