package com.realestate.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.realestate.testing.MainCoroutinesRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * Created by van.luong
 * on 14,June,2025
 */
class DataStoreDataSourceTest {
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var dataSource: DataStoreDataSource

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @get:Rule
    val tempFolder = TemporaryFolder()

    @Before
    fun setUp() {
        dataStore = PreferenceDataStoreFactory.create(
            scope = coroutinesRule.testScope,
            produceFile = { tempFolder.newFile("test.preferences_pb") }
        )
        dataSource = DataStoreDataSource(dataStore)
    }

    @Test
    fun `bookmarkedIds emits empty set by default`() = runTest {
        val ids = dataSource.bookmarkedIds.first()
        assert(ids.isEmpty())
    }

    @Test
    fun `toggleBookmark adds id to bookmarks`() = runTest {
        val id = "item1"
        dataSource.toggleBookmark(id)
        val ids = dataSource.bookmarkedIds.first()
        assert(ids.contains(id))
    }

    @Test
    fun `toggleBookmark removes id from bookmarks`() = runTest {
        val id = "item2"
        // Add first
        dataSource.toggleBookmark(id)
        // Remove
        dataSource.toggleBookmark(id)
        val ids = dataSource.bookmarkedIds.first()
        assert(!ids.contains(id))
    }

    @Test
    fun `toggleBookmark toggles multiple ids independently`() = runTest {
        val id1 = "itemA"
        val id2 = "itemB"
        dataSource.toggleBookmark(id1)
        dataSource.toggleBookmark(id2)
        var ids = dataSource.bookmarkedIds.first()
        assert(ids.containsAll(setOf(id1, id2)))
        // Remove one
        dataSource.toggleBookmark(id1)
        ids = dataSource.bookmarkedIds.first()
        assert(ids.contains(id2) && !ids.contains(id1))
    }
}