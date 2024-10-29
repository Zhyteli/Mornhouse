package com.test.mornhouse.data.repository

import com.test.mornhouse.data.database.NumberFactLocalDataSource
import com.test.mornhouse.data.mapper.toDomain
import com.test.mornhouse.data.model.NumberFactData
import com.test.mornhouse.data.network.NumberFactRemoteDataSource
import com.test.mornhouse.domain.model.NumberFact
import com.test.mornhouse.domain.repository.NumberFactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NumberFactRepositoryImpl @Inject constructor(
    private val remoteDataSource: NumberFactRemoteDataSource,
    private val localDataSource: NumberFactLocalDataSource
) : NumberFactRepository {
    override suspend fun getFact(number: String): String {
        return try {
            val response = remoteDataSource.getFact(number)
            val numberFactData = NumberFactData(number = number, fact = response)
            localDataSource.saveFact(numberFactData)
            response
        } catch (e: Exception) {
            e.printStackTrace()
            "Error fetching fact"
        }
    }

    override suspend fun getRandomFact(): String {
        return try {
            val response = remoteDataSource.getRandomFact()
            val number = response.substringBefore(" ").trim()
            val numberFactData = NumberFactData(number = number, fact = response)
            localDataSource.saveFact(numberFactData)
            response
        } catch (e: Exception) {
            e.printStackTrace()
            "Error fetching random fact"
        }
    }

    override fun getAllFacts(): Flow<List<NumberFact>> {
        return localDataSource.getAllFacts().map { list ->
            list.map { it.toDomain() }
        }
    }
}

