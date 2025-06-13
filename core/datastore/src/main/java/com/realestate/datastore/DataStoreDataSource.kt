package com.realestate.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by van.luong
 * on 14,June,2025
 *
 * DataStoreDataSource is a class that provides access to the DataStore for managing bookmark preferences.
 */
class DataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val bookmarkedIds: Flow<Set<String>> =
        dataStore.data.map { prefs -> prefs[BOOKMARK_KEY] ?: emptySet() }

    suspend fun toggleBookmark(id: String) {
        dataStore.edit { prefs ->
            val current = prefs[BOOKMARK_KEY] ?: emptySet()
            prefs[BOOKMARK_KEY] =
                if (current.contains(id)) current - id else current + id
        }
    }

    companion object {
        val BOOKMARK_KEY = stringSetPreferencesKey("bookmark_key")
    }
}