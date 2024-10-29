package com.test.mornhouse.domain.usecase

import com.test.mornhouse.domain.repository.NumberFactRepository
import javax.inject.Inject

class GetFactUseCase @Inject constructor(
    private val repository: NumberFactRepository
) {
    suspend operator fun invoke(number: String): String {
        return repository.getFact(number)
    }
}