package com.realestate.domain

import com.realestate.domain.repository.ListingRepository
import com.realestate.domain.usecase.GetBookmarkedPropertiesUseCase
import com.realestate.domain.usecase.ToggleBookmarkPropertyUseCase
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
class BookmarkUseCaseTest {
    private val repository = mock<ListingRepository>()

    @Test
    fun `ToggleBookmarkPropertyUseCase invokes repository toggleBookmark`() = runTest {
        val useCase = ToggleBookmarkPropertyUseCase(repository)
        val id = "property123"

        useCase.invoke(id)
        verify(repository).toggleBookmark(id)
    }

    @Test
    fun `GetBookmarkedPropertiesUseCase returns repository bookmarkedIds flow`() = runTest {
        val ids = setOf("id1", "id2")
        whenever(repository.bookmarkedIds).thenReturn(flowOf(ids))
        val useCase = GetBookmarkedPropertiesUseCase(repository)

        val result = useCase().first()
        assertEquals(ids, result)
    }
}