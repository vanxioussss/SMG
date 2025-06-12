package com.realestate.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.realestate.database.dao.RealEstateDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Base class for database tests, providing an in-memory database instance.
 */
@RunWith(RobolectricTestRunner::class)
abstract class DbTest {
    private lateinit var db: RealEstateDatabase
    protected lateinit var dao: RealEstateDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, RealEstateDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.realEstateDao()
    }

    @After
    fun closeDB() {
        db.close()
    }
}