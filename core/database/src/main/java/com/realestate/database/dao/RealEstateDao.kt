package com.realestate.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.realestate.database.entity.ListingEntity

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * DAO for accessing real estate data.
 */
@Dao
interface RealEstateDao {
    @Upsert
    suspend fun insertAllRealEstateListing(listing: List<ListingEntity>)

    @Query("SELECT * FROM realEstateListings ORDER BY id ASC")
    fun getAllRealEstateListings(): PagingSource<Int, ListingEntity>
}