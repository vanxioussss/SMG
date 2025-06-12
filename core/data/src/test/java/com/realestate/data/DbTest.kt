package com.realestate.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.realestate.database.RealEstateDatabase
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
    protected lateinit var db: RealEstateDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, RealEstateDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        init()
    }

    abstract fun init()

    @After
    fun closeDB() {
        db.close()
    }
}