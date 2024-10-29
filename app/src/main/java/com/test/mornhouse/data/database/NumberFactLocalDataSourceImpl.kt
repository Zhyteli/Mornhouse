package com.test.mornhouse.data.database

import com.test.mornhouse.data.model.NumberFactData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NumberFactLocalDataSourceImpl @Inject constructor(
    private val dao: NumberFactDao
) : NumberFactLocalDataSource {
    override suspend fun saveFact(numberFact: NumberFactData) {
        dao.insertFact(numberFact)
    }

    override fun getAllFacts(): Flow<List<NumberFactData>> {
        return dao.getAllFacts()
    }
}
