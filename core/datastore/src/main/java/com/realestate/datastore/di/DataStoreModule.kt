package com.realestate.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.datastore.preferences.preferencesDataStore
import com.realestate.datastore.DataStoreDataSource
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by van.luong
 * on 14,June,2025
 *
 * This module provides the DataStore instance for the application.
 */
val Context.userDataStore by preferencesDataStore(
    name = "real_estate_data_store"
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    /**
     * Provides the DataStore instance for user preferences.
     *
     * @param context The application context.
     * @return A singleton instance of DataStore<Preferences>.
     */
    @Provides
    @Singleton
    fun provideUserDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        context.userDataStore

    /**
     * Provides the [DataStoreDataSource] instance for accessing data store operations.
     *
     * @param dataStore The DataStore<Preferences> instance.
     * @return A singleton instance of [DataStoreDataSource].
     */
    @Provides
    @Singleton
    fun provideDataStoreDataSource(
        dataStore: DataStore<Preferences>
    ): DataStoreDataSource = DataStoreDataSource(dataStore)
}