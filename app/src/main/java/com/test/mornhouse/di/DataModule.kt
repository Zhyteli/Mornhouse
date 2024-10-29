package com.test.mornhouse.di

import com.test.mornhouse.data.database.NumberFactDao
import com.test.mornhouse.data.database.NumberFactLocalDataSource
import com.test.mornhouse.data.database.NumberFactLocalDataSourceImpl
import com.test.mornhouse.data.network.NumberFactRemoteDataSource
import com.test.mornhouse.data.network.NumberFactRemoteDataSourceImpl
import com.test.mornhouse.data.repository.NumberFactRepositoryImpl
import com.test.mornhouse.domain.repository.NumberFactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        client: HttpClient
    ): NumberFactRemoteDataSource {
        return NumberFactRemoteDataSourceImpl(client)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        dao: NumberFactDao
    ): NumberFactLocalDataSource {
        return NumberFactLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideNumberFactRepository(
        remoteDataSource: NumberFactRemoteDataSource,
        localDataSource: NumberFactLocalDataSource
    ): NumberFactRepository {
        return NumberFactRepositoryImpl(remoteDataSource, localDataSource)
    }
}
