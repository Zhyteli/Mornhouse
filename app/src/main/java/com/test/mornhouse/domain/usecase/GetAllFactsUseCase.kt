package com.test.mornhouse.domain.usecase

import com.test.mornhouse.domain.model.NumberFact
import com.test.mornhouse.domain.repository.NumberFactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFactsUseCase @Inject constructor(
    private val repository: NumberFactRepository
) {
    operator fun invoke(): Flow<List<NumberFact>> {
        return repository.getAllFacts()
    }
}