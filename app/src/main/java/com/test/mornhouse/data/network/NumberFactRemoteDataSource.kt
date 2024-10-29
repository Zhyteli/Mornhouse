package com.test.mornhouse.data.network

interface NumberFactRemoteDataSource {
    suspend fun getFact(number: String): String
    suspend fun getRandomFact(): String
}
