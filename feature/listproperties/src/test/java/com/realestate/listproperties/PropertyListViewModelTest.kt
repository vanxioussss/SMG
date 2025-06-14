package com.realestate.listproperties

import androidx.paging.PagingData
import app.cash.turbine.test
import com.realestate.domain.usecase.GetBookmarkedPropertiesUseCase
import com.realestate.domain.usecase.GetPropertiesListingUseCase
import com.realestate.domain.usecase.ToggleBookmarkPropertyUseCase
import com.realestate.testing.MainCoroutinesRule
import com.realestate.testing.util.MockDataUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

/**
 * Created by van.luong
 * on 14,June,2025
 */
class PropertyListViewModelTest {
    private val getPropertiesListingUseCase = mock<GetPropertiesListingUseCase>()
    private val toggleBookmarkPropertyUseCase = mock<ToggleBookmarkPropertyUseCase>()
    private val getBookmarkedPropertiesUseCase = mock<GetBookmarkedPropertiesUseCase>()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Test
    fun `bookmarkProperty calls toggleBookmarkPropertyUseCase`() = runTest {
        whenever(
            getPropertiesListingUseCase.invoke(
                1,
                20
            )
        ).thenReturn(flowOf(PagingData.empty()))
        whenever(getBookmarkedPropertiesUseCase.invoke()).thenReturn(MutableStateFlow(emptySet()))
        val viewModel = PropertyListViewModel(
            getPropertiesListingUseCase,
            toggleBookmarkPropertyUseCase,
            getBookmarkedPropertiesUseCase
        )

        viewModel.bookmarkProperty("property123")
        advanceUntilIdle()

        verify(toggleBookmarkPropertyUseCase).invoke("property123")
    }

    @Test
    fun `propertyListUiState emits Loading then Success`() = runTest {
        val pagingDataFlow = flowOf(PagingData.from(MockDataUtil.mockListingPropertiesList()))


        whenever(getPropertiesListingUseCase.invoke(1, 20)).thenReturn(pagingDataFlow)
        whenever(getBookmarkedPropertiesUseCase.invoke()).thenReturn(MutableStateFlow(emptySet()))

        val viewModel = PropertyListViewModel(
            getPropertiesListingUseCase,
            toggleBookmarkPropertyUseCase,
            getBookmarkedPropertiesUseCase
        )

        viewModel.propertyListUiState.test {
            assertEquals(PropertyUiState.Loading, awaitItem())

            val item = awaitItem()
            assertEquals(item is PropertyUiState.Success, true)
            cancelAndIgnoreRemainingEvents()
        }
    }
}