package com.realestate.testing.util

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
 * Utility object for generating mock data for testing purposes.
 */
object MockDataUtil {
    fun mockListingPropertiesList(): List<Listing> {
        return listOf(
            Listing(
                id = "1",
                price = Price(currency = "USD", Buy(area = "ALL", price = 99999999)),
                address = Address(
                    country = "CH",
                    locality = "La Chaux-de-Fonds",
                    postalCode = "2300",
                    region = "NE",
                    street = "Rue de la Paix 1"
                ),
                localization = Localization(
                    primary = "de",
                    locale = Locale(
                        attachments = listOf(Attachments(url = "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg")),
                        text = Text(
                            title = "Luxurious Apartment in the Heart of the City",
                        )
                    )
                )
            ),
            Listing(
                id = "2",
                price = Price(
                    currency = "CHF",
                    Buy(area = "ALL", price = 10000000, interval = "ONE_TIME")
                ),
                address = Address(
                    country = "CH",
                    locality = "La Chaux-de-Fonds",
                    postalCode = "2300",
                    region = "NE",
                    street = "Rue de la Paix 1"
                ),
                localization = Localization(
                    primary = "de",
                    locale = Locale(
                        attachments = listOf(Attachments(url = "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg")),
                        text = Text(
                            title = "Luxurious Apartment in the Heart of the City",
                        )
                    )
                )
            )
        )
    }
}