package com.realestate.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.realestate.model.realestate.Listing

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Entity class for [Listing] to store in the database.
 */
@Entity(tableName = "realEstateListings")
data class ListingEntity(
    @PrimaryKey val id: String,
    @Embedded(prefix = "price_") val price: PriceEntity,
    @Embedded(prefix = "address_") val address: AddressEntity,
    @Embedded(prefix = "localization_") val localization: LocalizationEntity
)
