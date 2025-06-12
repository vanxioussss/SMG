package com.realestate.model.realestate

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Represents a real estate listing with its details.
 */
data class Listing(
    val id: String,
    val price: Price,
    val address: Address,
    val localization: Localization
)
