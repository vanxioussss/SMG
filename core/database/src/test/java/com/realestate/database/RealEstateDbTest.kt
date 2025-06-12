package com.realestate.database

import androidx.paging.PagingSource
import com.realestate.database.mapper.toListingEntityList
import com.realestate.testing.util.MockDataUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * Created by van.luong
 * on 12,June,2025
 */
class RealEstateDbTest : DbTest() {
    @Test
    fun getPagedPropertiesTest() = runTest {
        val listings = MockDataUtil.mockListingPropertiesList().toListingEntityList()
        dao.insertAllRealEstateListing(listings)

        val pagingSource = dao.getAllRealEstateListings()
        val loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        val expected = PagingSource.LoadResult.Page(
            data = listings,
            prevKey = null,
            nextKey = null
        )

        assertEquals(expected.data, (loadResult as PagingSource.LoadResult.Page).data)
    }
}