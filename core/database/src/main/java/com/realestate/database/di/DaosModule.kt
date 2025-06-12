package com.realestate.database.di

import com.realestate.database.RealEstateDatabase
import com.realestate.database.dao.RealEstateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * This Hilt module provides the dependencies for the DAOs in the Real Estate database.
 */
@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    /**
     * Provides an instance of [RealEstateDao]
     *
     * @param database The [RealEstateDatabase] instance.
     * @return An instance of [RealEstateDao].
     */
    @Provides
    @Singleton
    fun providesRealEstateDao(
        database: RealEstateDatabase,
    ): RealEstateDao = database.realEstateDao()
}