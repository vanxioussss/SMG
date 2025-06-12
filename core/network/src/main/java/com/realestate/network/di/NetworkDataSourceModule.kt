package com.realestate.network.di

import com.realestate.network.datasource.RealEstateClient
import com.realestate.network.datasource.RealEstateNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Module for binding the RealEstateNetworkDataSource implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {
    /**
     * Binds the RealEstateClient implementation to the RealEstateNetworkDataSource interface.
     *
     * @param impl The RealEstateClient implementation.
     * @return The RealEstateNetworkDataSource instance.
     */
    @Binds
    fun binds(impl: RealEstateClient): RealEstateNetworkDataSource
}