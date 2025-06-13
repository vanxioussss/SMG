package com.realestate.domain.repository

import androidx.paging.PagingData
import com.realestate.model.realestate.Listing
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 13,June,2025
 *
 * This interface defines the contract for the ListingRepository.
 */
interface ListingRepository {
    fun getPropertyListings(page: Int, pageSize: Int): Flow<PagingData<Listing>>
}