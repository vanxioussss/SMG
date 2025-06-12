package com.realestate.network.di

import com.realestate.network.BuildConfig
import com.realestate.network.adapters.NetworkResponseCallFactory
import com.realestate.network.datasource.RealEstateClient
import com.realestate.network.datasource.RealEstateNetworkDataSource
import com.realestate.network.service.RealEstateService
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * NetworkModule is a Dagger module that provides network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    /**
     * Provides a singleton instance of [Call.Factory] which is used for making network calls.
     * This is typically an instance of [OkHttpClient].
     *
     * @return A singleton instance of [Call.Factory].
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    /**
     * Provides a singleton instance of [Retrofit]
     *
     * @param okHttpCallFactory A lazy reference to the [Call.Factory] instance.
     * @return A singleton instance of [Retrofit].
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpCallFactory: Lazy<Call.Factory>): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .callFactory(okHttpCallFactory.get())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseCallFactory.create())
            .build()
    }

    /**
     * Provides a singleton instance of [RealEstateService]
     *
     * @param retrofit A [Retrofit] instance used to create the service.
     * @return A singleton instance of [RealEstateService].
     */
    @Provides
    @Singleton
    fun provideRealEstateService(retrofit: Retrofit): RealEstateService =
        retrofit.create(RealEstateService::class.java)
}