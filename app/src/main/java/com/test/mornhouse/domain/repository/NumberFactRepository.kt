package com.test.mornhouse.domain.repository

import com.test.mornhouse.domain.model.NumberFact
import kotlinx.coroutines.flow.Flow

interface NumberFactRepository {
    suspend fun getFact(number: String): String
    suspend fun getRandomFact(): String
    fun getAllFacts(): Flow<List<NumberFact>>
}