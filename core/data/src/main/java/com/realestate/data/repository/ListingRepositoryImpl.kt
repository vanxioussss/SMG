package com.realestate.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.realestate.database.RealEstateDatabase
import com.realestate.database.mapper.toModel
import com.realestate.datastore.DataStoreDataSource
import com.realestate.domain.repository.ListingRepository
import com.realestate.model.realestate.Listing
import com.realestate.network.datasource.RealEstateNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by van.luong
 * on 13,June,2025
 *
 * This class implements the ListingRepository interface
 */
@OptIn(ExperimentalPagingApi::class)
class ListingRepositoryImpl @Inject constructor(
    private val networkDataSource: RealEstateNetworkDataSource,
    private val dataStoreDataSource: DataStoreDataSource,
    private val realEstateDatabase: RealEstateDatabase
) : ListingRepository {

    override fun getPropertyListings(page: Int, pageSize: Int): Flow<PagingData<Listing>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ListingRemoteMediator(realEstateDatabase, networkDataSource),
            pagingSourceFactory = { realEstateDatabase.realEstateDao().getAllRealEstateListings() }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toModel()
            }
        }
    }

    override suspend fun toggleBookmark(id: String) {
        dataStoreDataSource.toggleBookmark(id)
    }

    override val bookmarkedIds: Flow<Set<String>>
        get() = dataStoreDataSource.bookmarkedIds
}