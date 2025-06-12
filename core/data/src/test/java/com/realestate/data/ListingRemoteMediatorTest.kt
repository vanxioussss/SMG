package com.realestate.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.realestate.common.resource.Resource
import com.realestate.data.repository.ListingRemoteMediator
import com.realestate.database.entity.ListingEntity
import com.realestate.network.datasource.RealEstateNetworkDataSource
import com.realestate.network.dto.realestate.RealEstateDto
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * Created by van.luong
 * on 13,June,2025
 */
@OptIn(ExperimentalPagingApi::class)
class ListingRemoteMediatorTest : DbTest() {
    private val mockNetworkDataSource: RealEstateNetworkDataSource = mock()
    private lateinit var mediator: ListingRemoteMediator

    override fun init() {
        mediator = ListingRemoteMediator(db, mockNetworkDataSource)
    }

    @Test
    fun loadNoResultsTest() = runTest {
        val emptyResource = Resource.Success(
            RealEstateDto(
                results = emptyList()
            )
        )

        whenever(mockNetworkDataSource.getAllProperties()).thenReturn(
            emptyResource
        )

        val pagingState =
            PagingState<Int, ListingEntity>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 10),
                leadingPlaceholderCount = 0
            )

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun exceptionTest() = runTest {
        whenever(mockNetworkDataSource.getAllProperties()).thenThrow(
            RuntimeException("Network error")
        )

        val pagingState =
            PagingState<Int, ListingEntity>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 10),
                leadingPlaceholderCount = 0
            )

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertTrue(result is RemoteMediator.MediatorResult.Error)
        assertTrue((result as RemoteMediator.MediatorResult.Error).throwable is RuntimeException)
    }
}