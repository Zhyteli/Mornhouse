package com.test.mornhouse.di

import com.test.mornhouse.domain.repository.NumberFactRepository
import com.test.mornhouse.domain.usecase.GetAllFactsUseCase
import com.test.mornhouse.domain.usecase.GetFactUseCase
import com.test.mornhouse.domain.usecase.GetRandomFactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetAllFactsUseCase(repository: NumberFactRepository): GetAllFactsUseCase {
        return GetAllFactsUseCase(repository)
    }

    @Provides
    fun provideGetFactUseCase(repository: NumberFactRepository): GetFactUseCase {
        return GetFactUseCase(repository)
    }

    @Provides
    fun provideGetRandomFactUseCase(repository: NumberFactRepository): GetRandomFactUseCase {
        return GetRandomFactUseCase(repository)
    }
}
