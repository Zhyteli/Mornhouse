package com.test.mornhouse.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class NumberFactRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : NumberFactRemoteDataSource {
    override suspend fun getFact(number: String): String {
        return client.get("${ConstNet.FACT}$number").bodyAsText()
    }

    override suspend fun getRandomFact(): String {
        return client.get("${ConstNet.FACT}${ConstNet.RANDOM}").bodyAsText()
    }
}
