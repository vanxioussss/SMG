package com.realestate.listproperties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.realestate.domain.usecase.GetPropertiesListingUseCase
import com.realestate.model.realestate.Listing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
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
    getPropertiesListingUseCase: GetPropertiesListingUseCase
) : ViewModel() {

    val listings = getPropertiesListingUseCase(1, 20)
        .cachedIn(viewModelScope)

    val propertyListUiState: StateFlow<PropertyUiState> = listings
        .map { PropertyUiState.Success }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PropertyUiState.Loading
        )
}