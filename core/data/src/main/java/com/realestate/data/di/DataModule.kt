package com.realestate.data.di

import com.realestate.data.repository.ListingRepositoryImpl
import com.realestate.data.util.network.NetworkMonitor
import com.realestate.data.util.network.NetworkStatusTracker
import com.realestate.domain.repository.ListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by van.luong
 * on 13,June,2025
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideListingRepository(impl: ListingRepositoryImpl): ListingRepository = impl

    @Provides
    fun provideNetworkStatusTracker(impl: NetworkStatusTracker): NetworkMonitor = impl
}