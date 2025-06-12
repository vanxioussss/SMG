package com.realestate.database.di

import android.content.Context
import androidx.room.Room
import com.realestate.database.RealEstateDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * This Hilt module provides the dependencies for the Real Estate database.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesRealEstateDatabase(@ApplicationContext context: Context): RealEstateDatabase =
        Room.databaseBuilder(
            context,
            RealEstateDatabase::class.java,
            "real_estate.db"
        ).build()
}