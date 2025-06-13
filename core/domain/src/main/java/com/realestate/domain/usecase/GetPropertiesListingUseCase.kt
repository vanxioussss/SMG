package com.realestate.domain.usecase

import androidx.paging.PagingData
import com.realestate.domain.repository.ListingRepository
import com.realestate.model.realestate.Listing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by van.luong
 * on 13,June,2025
 *
 * This use case retrieves a paginated list of property listings.
 */
class GetPropertiesListingUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    operator fun invoke(page: Int, pageSize: Int): Flow<PagingData<Listing>> =
        listingRepository.getPropertyListings(page, pageSize).flowOn(Dispatchers.IO)
}