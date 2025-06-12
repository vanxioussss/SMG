package com.realestate.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.realestate.database.converters.AttachmentsListConverter
import com.realestate.database.dao.RealEstateDao
import com.realestate.database.entity.ListingEntity

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Database class for managing real estate listings.
 */
@Database(
    entities = [ListingEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    AttachmentsListConverter::class
)
abstract class RealEstateDatabase : RoomDatabase() {
    abstract fun realEstateDao(): RealEstateDao
}