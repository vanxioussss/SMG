package com.realestate.network.dto.realestate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Data Transfer Object (DTO) for list of real estate results.
 */
@JsonClass(generateAdapter = true)
data class RealEstateDto(
    @field:Json(name = "results") val results: List<ResultDto>
)

/**
 * Data Transfer Object (DTO) representing a real estate listing.
 */
@JsonClass(generateAdapter = true)
data class ResultDto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "listing") val listingDto: ListingDto
)
