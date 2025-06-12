package com.realestate.network.mapper

import com.realestate.model.realestate.Address
import com.realestate.model.realestate.Attachments
import com.realestate.model.realestate.Buy
import com.realestate.model.realestate.De
import com.realestate.model.realestate.Listing
import com.realestate.model.realestate.Localization
import com.realestate.model.realestate.Price
import com.realestate.model.realestate.Text
import com.realestate.network.dto.realestate.AddressDto
import com.realestate.network.dto.realestate.AttachmentsDto
import com.realestate.network.dto.realestate.BuyDto
import com.realestate.network.dto.realestate.DeDto
import com.realestate.network.dto.realestate.ListingDto
import com.realestate.network.dto.realestate.LocalizationDto
import com.realestate.network.dto.realestate.PriceDto
import com.realestate.network.dto.realestate.TextDto

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Extension function to convert [AddressDto] to [Address] model.
 *
 * @return [Address] model instance.
 */
fun AddressDto.toModel(): Address {
    return Address(
        country = country,
        locality = locality,
        postalCode = postalCode,
        region = region,
        street = street,
    )
}

/**
 * Extension function to convert [Address] model to [AddressDto].
 *
 * @return [AddressDto] instance.
 */
fun Address.toDto(): AddressDto {
    return AddressDto(
        country = country,
        locality = locality,
        postalCode = postalCode,
        region = region,
        street = street,
    )
}

/**
 * Extension function to convert [BuyDto] to [Buy] model.
 *
 * @return [Buy] model instance.
 */
fun BuyDto.toModel(): Buy {
    return Buy(
        area = area,
        price = price,
        interval = interval,
    )
}

/**
 * Extension function to convert [Buy] model to [BuyDto].
 *
 * @return [BuyDto] instance.
 */
fun Buy.toDto(): BuyDto {
    return BuyDto(
        area = area,
        price = price,
        interval = interval,
    )
}

/**
 * Extension function to convert [PriceDto] to [Price] model.
 *
 * @return [Price] model instance.
 */
fun PriceDto.toModel(): Price {
    return Price(
        currency = currency,
        buy = buy.toModel(),
    )
}

/**
 * Extension function to convert [Price] model to [PriceDto].
 *
 * @return [PriceDto] instance.
 */
fun Price.toDto(): PriceDto {
    return PriceDto(
        currency = currency,
        buy = buy.toDto(),
    )
}

/**
 * Extension function to convert [TextDto] to [Text] model.
 *
 * @return [Text] model instance.
 */
fun TextDto.toModel(): Text {
    return Text(
        title = title
    )
}

/**
 * Extension function to convert [Text] model to [TextDto].
 *
 * @return [TextDto] instance.
 */
fun Text.toDto(): TextDto {
    return TextDto(
        title = title
    )
}

/**
 * Extension function to convert [AttachmentsDto] to [Attachments] model.
 *
 * @return [Attachments] model instance.
 */
fun AttachmentsDto.toModel(): Attachments {
    return Attachments(
        url = url
    )
}

/**
 * Extension function to convert [Attachments] model to [AttachmentsDto].
 *
 * @return [AttachmentsDto] instance.
 */
fun Attachments.toDto(): AttachmentsDto {
    return AttachmentsDto(
        url = url
    )
}

/**
 * Extension function to convert a list of [AttachmentsDto] to a list of [Attachments] models.
 *
 * @return List of [Attachments] model instances.
 */
fun List<AttachmentsDto>.toModelList(): List<Attachments> {
    return map { it.toModel() }
}

/**
 * Extension function to convert a list of [Attachments] models to a list of [AttachmentsDto].
 *
 * @return List of [AttachmentsDto] instances.
 */
fun List<Attachments>.toDtoList(): List<AttachmentsDto> {
    return map { it.toDto() }
}

/**
 * Extension function to convert [DeDto] to [De] model.
 *
 * @return [De] model instance.
 */
fun DeDto.toModel(): De {
    return De(
        attachments = attachments.toModelList(),
        text = text.toModel()
    )
}

/**
 * Extension function to convert [De] model to [DeDto].
 *
 * @return [DeDto] instance.
 */
fun De.toDto(): DeDto {
    return DeDto(
        attachments = attachments.toDtoList(),
        text = text.toDto()
    )
}

/**
 * Extension function to convert [LocalizationDto] to [Localization] model.
 *
 * @return [Localization] model instance.
 */
fun LocalizationDto.toModel(): Localization {
    return Localization(
        primary = primary,
        de = de.toModel(),
    )
}

/**
 * Extension function to convert [Localization] model to [LocalizationDto].
 *
 * @return [LocalizationDto] instance.
 */
fun Localization.toDto(): LocalizationDto {
    return LocalizationDto(
        primary = primary,
        de = de.toDto(),
    )
}

/**
 * Extension function to convert [ListingDto] to [Listing] model.
 *
 * @return [Listing] model instance.
 */
fun ListingDto.toModel(): Listing {
    return Listing(
        id = id,
        price = price.toModel(),
        address = address.toModel(),
        localization = localization.toModel()
    )
}

/**
 * Extension function to convert [Listing] model to [ListingDto].
 *
 * @return [ListingDto] instance.
 */
fun Listing.toDto(): ListingDto {
    return ListingDto(
        id = id,
        price = price.toDto(),
        address = address.toDto(),
        localization = localization.toDto()
    )
}
