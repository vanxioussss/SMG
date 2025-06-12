//package com.realestate.data
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.paging.testing.asSnapshot
//import com.realestate.data.repository.ListingRemoteMediator
//import com.realestate.data.repository.ListingRepositoryImpl
//import com.realestate.database.dao.RealEstateDao
//import com.realestate.database.mapper.toListingEntityList
//import com.realestate.domain.repository.ListingRepository
//import com.realestate.model.realestate.Listing
//import com.realestate.network.datasource.RealEstateNetworkDataSource
//import com.realestate.testing.MainCoroutinesRule
//import com.realestate.testing.util.MockDataUtil
//import junit.framework.TestCase.assertEquals
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.test.advanceUntilIdle
//import kotlinx.coroutines.test.runTest
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.mock
//import org.mockito.kotlin.whenever
//
///**
// * Created by van.luong
// * on 13,June,2025
// */
//class ListingRepositoryTest : DbTest() {
//    private lateinit var repository: ListingRepository
//    private val networkDataSource: RealEstateNetworkDataSource = mock()
//    private val dao: RealEstateDao = mock()
//    private lateinit var remoteMediator: ListingRemoteMediator
//
//    @get:Rule
//    val coroutinesRule = MainCoroutinesRule()
//
//    override fun init() {
//        repository = ListingRepositoryImpl(networkDataSource, db)
//        remoteMediator = ListingRemoteMediator(db, networkDataSource)
//    }
//
//    @OptIn(ExperimentalPagingApi::class)
//    @Test
//    fun getPropertyListingsTest() = runTest {
//        val mockData = MockDataUtil.mockListingPropertiesList().toListingEntityList()
//        val pagingSource = FakePagingSource(mockData)
//
//        whenever(dao.getAllRealEstateListings()).thenReturn(pagingSource)
//
//        val mockMediatorResult =
//            RemoteMediator.MediatorResult.Success(endOfPaginationReached = true)
//        whenever(
//            remoteMediator.load(
//                LoadType.REFRESH,
//                PagingState()
//            )
//        ).thenReturn(mockMediatorResult)
//
//        val propertyList = mutableListOf<Listing>()
//        val job = launch(coroutinesRule.testDispatcher) {
//            repository.getPropertyListings(1, 20).asSnapshot().toCollection(propertyList)
//        }
//        advanceUntilIdle()
//
//        assertEquals(propertyList[0].id, mockData[0].id)
//
//        job.cancel()
//    }
//}
//
//class FakePagingSource<T : Any>(
//    private val items: List<T>
//) : PagingSource<Int, T>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
//        return LoadResult.Page(
//            data = items,
//            prevKey = null,
//            nextKey = null
//        )
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
//}