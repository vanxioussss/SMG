package com.realestate.domain.usecase

import com.realestate.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by van.luong
 * on 14,June,2025
 */

/**
 * Use case for toggling property listings bookmark
 *
 * @property listingRepository The repository to toggle the bookmark status of a listing.
 */
class ToggleBookmarkPropertyUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {
    suspend operator fun invoke(id: String) {
        listingRepository.toggleBookmark(id)
    }
}

/**
 * Use case for getting bookmarked property listings.
 *
 * @property listingRepository The repository to fetch bookmarked property listings.
 */
class GetBookmarkedPropertiesUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {
    operator fun invoke(): Flow<Set<String>> = listingRepository.bookmarkedIds
}