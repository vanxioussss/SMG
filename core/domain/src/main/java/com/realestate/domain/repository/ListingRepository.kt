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
    /**
     * Fetches property listings with pagination support.
     *
     * @param page The page number to fetch.
     * @param pageSize The number of items per page.
     * @return A Flow of PagingData containing a list of Listing objects.
     */
    fun getPropertyListings(page: Int, pageSize: Int): Flow<PagingData<Listing>>

    /**
     * Toggles the bookmark status of a listing.
     *
     * @param id The unique identifier of the listing to toggle the bookmark status for.
     */
    suspend fun toggleBookmark(id: String)

    /**
     * A Flow that emits a set of bookmarked listing IDs.
     *
     * This can be used to observe changes in the bookmarked listings.
     */
    val bookmarkedIds: Flow<Set<String>>
}