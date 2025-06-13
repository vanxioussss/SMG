package com.realestate.domain

import androidx.paging.PagingData
import com.realestate.domain.repository.ListingRepository
import com.realestate.domain.usecase.GetPropertiesListingUseCase
import com.realestate.testing.util.MockDataUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

/**
 * Created by van.luong
 * on 14,June,2025
 */
class GetPropertiesListingUseCaseTest {
    private val repository = mock<ListingRepository>()

    @Test
    fun `invoke returns repository paging data flow`() = runTest {
        val page = 1
        val pageSize = 20
        val pagingData = PagingData.from(MockDataUtil.mockListingPropertiesList())
        whenever(repository.getPropertyListings(page, pageSize)).thenReturn(flowOf(pagingData))
        val useCase = GetPropertiesListingUseCase(repository)

        val result = useCase(page, pageSize)
        assertEquals(pagingData, result.first())
        verify(repository).getPropertyListings(page, pageSize)
    }
}