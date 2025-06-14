package com.realestate.listproperties

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.realestate.listproperties.util.toCurrencySymbol
import com.realestate.listproperties.util.toReadablePrice
import com.realestate.model.realestate.Listing
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 13,June,2025
 */
@Composable
fun PropertyListRoute(
    modifier: Modifier = Modifier,
    viewModel: PropertyListViewModel = hiltViewModel()
) {
    val propertyUiState by viewModel.propertyListUiState.collectAsStateWithLifecycle()
    PropertyListScreen(
        modifier = modifier,
        propertyUiState = propertyUiState,
        onBookmarkClick = { viewModel.bookmarkProperty(it.id) }
    )
}

@Composable
fun PropertyListScreen(
    modifier: Modifier,
    propertyUiState: PropertyUiState,
    onBookmarkClick: (Listing) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Property Listings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        when (propertyUiState) {
            is PropertyUiState.Success -> {
                val pagingItem = propertyUiState.pagingDataFlow.collectAsLazyPagingItems()
                val bookmarkedIds = propertyUiState.bookmarkedIds

                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(pagingItem.itemCount, key = pagingItem.itemKey { it.id }) { index ->
                        pagingItem[index]?.let {
                            PropertyItem(
                                listing = it,
                                isBookmarked = bookmarkedIds.contains(it.id),
                                onBookmarkClick = onBookmarkClick
                            )
                        }
                    }
                }
            }

            is PropertyUiState.Loading -> {
                // Show loading indicator
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier)
                }
            }

            is PropertyUiState.Error -> {
                // Show error message
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = (propertyUiState as PropertyUiState.Error).message,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyItem(
    listing: Listing,
    isBookmarked: Boolean,
    onBookmarkClick: (Listing) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val address = listing.address
        val price = listing.price

        Box(modifier = Modifier.height(320.dp)) {
            GlideImage(
                model = listing.localization.locale.attachments.firstOrNull()?.url,
                contentDescription = listing.localization.locale.text.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                onClick = { onBookmarkClick(listing) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(Color.White.copy(alpha = 0.8f), shape = CircleShape)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Black
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(0.6f)
                    ) {
                        Text(
                            text = listing.localization.locale.text.title,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "Location",
                                tint = Color.Gray
                            )
                            Text(
                                text = "${address.street}, ${address.locality}, ${address.region}",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "${price.currency.toCurrencySymbol()}${price.buy.price.toReadablePrice()}/",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                                fontSize = 18.sp
                            )
                            Text(
                                text = price.buy.interval ?: "One-time",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

        }
    }
}

/**
 * Represents the UI state for the property listing screen.
 */
sealed interface PropertyUiState {
    /**
     * Represents a successful state with a list of property listings.
     *
     * @property pagingDataFlow A flow of paging data containing the property listings.
     * @property bookmarkedIds A set of bookmarked property IDs.
     */
    data class Success(
        val pagingDataFlow: Flow<PagingData<Listing>>,
        val bookmarkedIds: Set<String> = emptySet()
    ) : PropertyUiState

    /**
     * Represents a loading state.
     */
    data object Loading : PropertyUiState

    /**
     * Represents an error state with a message.
     *
     * @property message The error message.
     */
    data class Error(val message: String) : PropertyUiState
}

//@Preview
//@Composable
//fun PropertyItemPreview() {
//    PropertyListScreen(
//        modifier = Modifier.fillMaxSize(),
//        propertyUiState = TODO(),
//        pagingItem = TODO(),
//        bookmarkedIds = TODO(),
//        onBookmarkClick = TODO()
//    )
//}