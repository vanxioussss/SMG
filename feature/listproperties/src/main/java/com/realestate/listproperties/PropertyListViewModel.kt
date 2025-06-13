package com.realestate.listproperties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.realestate.domain.usecase.GetBookmarkedPropertiesUseCase
import com.realestate.domain.usecase.GetPropertiesListingUseCase
import com.realestate.domain.usecase.ToggleBookmarkPropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by van.luong
 * on 13,June,2025
 *
 * ViewModel for managing the state of the property listings.
 */
@HiltViewModel
class PropertyListViewModel @Inject constructor(
    getPropertiesListingUseCase: GetPropertiesListingUseCase,
    private val toggleBookmarkPropertyUseCase: ToggleBookmarkPropertyUseCase,
    getBookmarkedPropertiesUseCase: GetBookmarkedPropertiesUseCase
) : ViewModel() {

    val listings = getPropertiesListingUseCase(1, 20)
        .cachedIn(viewModelScope)

    val bookmarkedIds = getBookmarkedPropertiesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptySet()
        )

    val propertyListUiState: StateFlow<PropertyUiState> = listings
        .map { PropertyUiState.Success }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PropertyUiState.Loading
        )

    fun bookmarkProperty(propertyId: String) {
        viewModelScope.launch {
            toggleBookmarkPropertyUseCase(propertyId)
        }
    }
}