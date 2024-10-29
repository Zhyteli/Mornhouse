package com.test.mornhouse.domain.usecase

import com.test.mornhouse.domain.repository.NumberFactRepository
import javax.inject.Inject

class GetRandomFactUseCase @Inject constructor(
    private val repository: NumberFactRepository
) {
    suspend operator fun invoke(): String {
        return repository.getRandomFact()
    }
}