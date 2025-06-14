package com.realestate.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.realestate.common.resource.Resource
import com.realestate.database.RealEstateDatabase
import com.realestate.database.entity.ListingEntity
import com.realestate.database.mapper.toListingEntityList
import com.realestate.network.datasource.RealEstateNetworkDataSource
import com.realestate.network.mapper.mapToListingDtoList
import com.realestate.network.mapper.toListingModelList
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by van.luong
 * on 13,June,2025
 *
 * This class is responsible for loading data from the network and caching it in the local database.
 */
@OptIn(ExperimentalPagingApi::class)
class ListingRemoteMediator @Inject constructor(
    private val realEstateDb: RealEstateDatabase,
    private val networkDataSource: RealEstateNetworkDataSource
) : RemoteMediator<Int, ListingEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ListingEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1

                LoadType.PREPEND -> {
                    // Prepend is not supported, return no result
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1 // If no items, start from page 1
                    } else {
                        // You might store page keys if available
                        val lastPage = (state.pages.size) // or track in DB
                        lastPage + 1
                    }
                }
            }

            // Small dataset - returns all listings
            when (val networkResponse = networkDataSource.getAllProperties()) {
                is Resource.DataError -> {
                    // Handle error case
                    return MediatorResult.Error(Exception(networkResponse.error))
                }

                is Resource.ServerError -> {
                    // Handle error case
                    return MediatorResult.Error(Exception(networkResponse.error.message))
                }

                is Resource.Loading -> {
                    // Handle loading state if needed
                }

                is Resource.Success -> {
                    // Successfully fetched data
                    realEstateDb.withTransaction {
                        // Clear the existing data if refreshing
                        if (loadType == LoadType.REFRESH) {
                            realEstateDb.realEstateDao().clearAll()
                        }

                        // Insert new listings into the database
                        val results = networkResponse.body.results

                        // Convert the ListingDto to ListingEntity
                        val entityList =
                            results.mapToListingDtoList().toListingModelList().toListingEntityList()
                        realEstateDb.realEstateDao().insertAllRealEstateListing(entityList)
                    }
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = true
            )
        } catch (exception: IOException) {
            // Network error
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            // HTTP error
            return MediatorResult.Error(exception)
        } catch (exception: Exception) {
            // Other errors
            return MediatorResult.Error(exception)
        }
    }
}