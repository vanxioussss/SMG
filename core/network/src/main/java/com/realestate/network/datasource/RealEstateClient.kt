package com.realestate.network.datasource

import com.realestate.common.resource.Resource
import com.realestate.network.dto.realestate.RealEstateDto
import com.realestate.network.service.RealEstateService
import javax.inject.Inject

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Implementation of the [RealEstateNetworkDataSource] interface that provides methods
 */
class RealEstateClient @Inject constructor(
    private val realEstateService: RealEstateService
) : RealEstateNetworkDataSource {

    /**
     * Fetches all properties from the Real Estate service.
     *
     * @return A [Resource] containing a [RealEstateDto] with the list of properties.
     */
    override suspend fun getAllProperties(): Resource<RealEstateDto> =
        realEstateService.getAllProperties()
}