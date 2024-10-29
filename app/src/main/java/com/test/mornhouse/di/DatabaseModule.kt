package com.test.mornhouse.di

import android.app.Application
import androidx.room.Room
import com.test.mornhouse.data.database.NumberFactDao
import com.test.mornhouse.data.database.NumberFactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): NumberFactDatabase {
        return Room.databaseBuilder(app, NumberFactDatabase::class.java, "number_facts_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideNumberFactDao(db: NumberFactDatabase): NumberFactDao {
        return db.numberFactDao()
    }
}
