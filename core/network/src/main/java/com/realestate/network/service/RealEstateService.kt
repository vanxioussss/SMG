package com.realestate.network.service

import com.realestate.common.resource.Resource
import com.realestate.network.dto.realestate.RealEstateDto
import retrofit2.http.GET

/**
 * Created by van.luong
 * on 12,June,2025
 */
interface RealEstateService {

    /***
     * Fetches all real estate properties.
     *
     * @return A [Resource] containing a [RealEstateDto] with the list of properties.
     */
    @GET("properties")
    suspend fun getAllProperties(): Resource<RealEstateDto>
}