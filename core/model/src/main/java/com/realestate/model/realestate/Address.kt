package com.realestate.model.realestate

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Represents a real estate address with its details.
 */
data class Address(
    val country: String,
    val locality: String,
    val postalCode: String,
    val region: String,
    val street: String,
)