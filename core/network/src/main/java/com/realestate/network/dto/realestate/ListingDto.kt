package com.realestate.network.dto.realestate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/***
 * Created by van.luong
 * on 12,June,2025
 *
 * Data Transfer Object (DTO) for the Real Estate's listing information.
 *
 * @see PriceDto
 * @see AddressDto
 * @see LocalizationDto
 */
@JsonClass(generateAdapter = true)
data class ListingDto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "prices") val price: PriceDto,
    @field:Json(name = "address") val address: AddressDto,
    @field:Json(name = "localization") val localization: LocalizationDto
)
