package com.realestate.database.entity

import com.realestate.model.realestate.Address

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Represents a real estate [Address] entity for database storage.
 */
data class AddressEntity(
    val country: String,
    val locality: String,
    val postalCode: String,
    val region: String,
    val street: String? = null
)