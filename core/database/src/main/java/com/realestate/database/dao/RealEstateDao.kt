package com.realestate.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.realestate.database.entity.ListingEntity

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * DAO for accessing real estate data.
 */
@Dao
interface RealEstateDao {
    @Query("SELECT * FROM realEstateListings ORDER BY id ASC")
    suspend fun getAllRealEstateListings(): PagingSource<Int, ListingEntity>
}