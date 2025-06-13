package com.realestate.listproperties

import androidx.paging.PagingData
import app.cash.turbine.test
import com.realestate.domain.usecase.GetBookmarkedPropertiesUseCase
import com.realestate.domain.usecase.GetPropertiesListingUseCase
import com.realestate.domain.usecase.ToggleBookmarkPropertyUseCase
import com.realestate.model.realestate.Listing
import com.realestate.testing.MainCoroutinesRule
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
    fun `bookmarkedIds emits from use case`() = runTest {
        val ids = setOf("id1", "id2")
        whenever(
            getPropertiesListingUseCase.invoke(
                1,
                20
            )
        ).thenReturn(flowOf(PagingData.empty()))
        whenever(getBookmarkedPropertiesUseCase.invoke()).thenReturn(MutableStateFlow(ids))

        val viewModel = PropertyListViewModel(
            getPropertiesListingUseCase,
            toggleBookmarkPropertyUseCase,
            getBookmarkedPropertiesUseCase
        )

        viewModel.bookmarkedIds.test {
            // Initial state should be empty set
            awaitItem()

            // After the flow emits, it should contain the ids
            assertEquals(ids, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

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
        val pagingData = PagingData.from(listOf<Listing>())
        whenever(getPropertiesListingUseCase.invoke(1, 20)).thenReturn(flowOf(pagingData))
        whenever(getBookmarkedPropertiesUseCase.invoke()).thenReturn(MutableStateFlow(emptySet()))

        val viewModel = PropertyListViewModel(
            getPropertiesListingUseCase,
            toggleBookmarkPropertyUseCase,
            getBookmarkedPropertiesUseCase
        )

        viewModel.propertyListUiState.test {
            assertEquals(PropertyUiState.Loading, awaitItem())
            assertEquals(PropertyUiState.Success, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}