package com.realestate.network.datasource

import com.realestate.common.resource.Resource
import com.realestate.network.dto.realestate.RealEstateDto

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Data source interface for fetching real estate properties from the network.
 */
interface RealEstateNetworkDataSource {
    /**
     * Fetches all real estate properties.
     *
     * @return A [Resource] containing a [RealEstateDto] with the list of properties or an error.
     */
    suspend fun getAllProperties(): Resource<RealEstateDto>
}