package com.realestate.network

import com.realestate.common.resource.Resource
import com.realestate.network.datasource.RealEstateClient
import com.realestate.network.datasource.RealEstateNetworkDataSource
import com.realestate.network.service.RealEstateService
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.fail
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Created by van.luong
 * on 12,June,2025
 */
class RealEstateServiceTest : ApiAbstract<RealEstateService>() {
    private lateinit var networkDataSource: RealEstateNetworkDataSource
    private lateinit var realEstateService: RealEstateService

    @Before
    fun setUp() {
        realEstateService = createService(RealEstateService::class.java)
        networkDataSource = RealEstateClient(realEstateService)
    }

    @Test
    fun getAllPropertiesTest() = runTest {
        enqueueResponse("/RealEstateResponse.json")
        when (val response = networkDataSource.getAllProperties()) {
            is Resource.Success -> {
                val networkResult = response.body

                assertNotNull(networkResult)

                assert(networkResult.results.isNotEmpty())

                val listingDto = networkResult.results.firstOrNull()!!.listingDto
                assertNotNull(listingDto)
                assert(listingDto.id == "104123262")

                val price = listingDto.price
                assert(price.currency == "CHF")
                assert(price.buy.price == 9999999L)
                assert(price.buy.area == "ALL")
                assert(price.buy.interval == "ONETIME")

                val address = listingDto.address
                assert(address.country == "CH")
                assert(address.locality == "La Brévine")
                assert(address.postalCode == "2406")
                assert(address.region == "NE")
                assert(address.street == "Musterstrasse 999")

                val localization = listingDto.localization
                assert(localization.primary == "de")
                assert(localization.locale.attachments[0].url == "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg")
                assert(localization.locale.text.title == "Luxuriöses Einfamilienhaus mit Pool - Musterinserat")
            }

            is Resource.ServerError -> {
                fail("Expected success but got server error: ${response.error.message}")
            }

            is Resource.DataError -> {
                fail("Expected success but got network error: ${response.error.message}")
            }

            Resource.Loading -> {
                fail("Expected success but got loading state")
            }
        }
    }
}