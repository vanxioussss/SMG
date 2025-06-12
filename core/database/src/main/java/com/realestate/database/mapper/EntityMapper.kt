package com.realestate.database.mapper

import com.realestate.database.entity.AddressEntity
import com.realestate.database.entity.AttachmentsEntity
import com.realestate.database.entity.BuyEntity
import com.realestate.database.entity.ListingEntity
import com.realestate.database.entity.LocaleEntity
import com.realestate.database.entity.LocalizationEntity
import com.realestate.database.entity.PriceEntity
import com.realestate.database.entity.TextEntity
import com.realestate.model.realestate.Address
import com.realestate.model.realestate.Attachments
import com.realestate.model.realestate.Buy
import com.realestate.model.realestate.Listing
import com.realestate.model.realestate.Locale
import com.realestate.model.realestate.Localization
import com.realestate.model.realestate.Price
import com.realestate.model.realestate.Text

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Mapper functions to convert between database entities and model objects.
 */
fun ListingEntity.toModel(): Listing {
    return Listing(
        id = id,
        price = price.toModel(),
        address = address.toModel(),
        localization = localization.toModel()
    )
}

fun Listing.toEntity(): ListingEntity {
    return ListingEntity(
        id = id,
        price = price.toEntity(),
        address = address.toEntity(),
        localization = localization.toEntity()
    )
}

fun List<ListingEntity>.toListingModelList(): List<Listing> {
    return map { it.toModel() }
}

fun List<Listing>.toListingEntityList(): List<ListingEntity> {
    return map { it.toEntity() }
}

fun PriceEntity.toModel(): Price {
    return Price(
        currency = currency,
        buy = buy.toModel(),
    )
}

fun Price.toEntity(): PriceEntity {
    return PriceEntity(
        currency = currency,
        buy = buy.toEntity(),
    )
}

fun BuyEntity.toModel(): Buy {
    return Buy(
        area = area,
        price = price,
        interval = interval
    )
}

fun Buy.toEntity(): BuyEntity {
    return BuyEntity(
        area = area,
        price = price,
        interval = interval
    )
}

fun AddressEntity.toModel(): Address {
    return Address(
        country = country,
        locality = locality,
        postalCode = postalCode,
        region = region,
        street = street
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        country = country,
        locality = locality,
        postalCode = postalCode,
        region = region,
        street = street
    )
}

fun LocalizationEntity.toModel(): Localization {
    return Localization(
        primary = primary,
        locale = locale.toModel(),
    )
}

fun Localization.toEntity(): LocalizationEntity {
    return LocalizationEntity(
        primary = primary,
        locale = locale.toEntity(),
    )
}

fun LocaleEntity.toModel(): Locale {
    return Locale(
        attachments = attachments.toAttachmentsModelList(),
        text = text.toModel()
    )
}

fun Locale.toEntity(): LocaleEntity {
    return LocaleEntity(
        attachments = attachments.toAttachmentsEntityList(),
        text = text.toEntity()
    )
}

fun AttachmentsEntity.toModel(): Attachments {
    return Attachments(
        url = url
    )
}

fun Attachments.toEntity(): AttachmentsEntity {
    return AttachmentsEntity(
        url = url
    )
}

fun List<AttachmentsEntity>.toAttachmentsModelList(): List<Attachments> {
    return map { it.toModel() }
}

fun List<Attachments>.toAttachmentsEntityList(): List<AttachmentsEntity> {
    return map { it.toEntity() }
}

fun TextEntity.toModel(): Text {
    return Text(
        title = title
    )
}

fun Text.toEntity(): TextEntity {
    return TextEntity(
        title = title
    )
}
