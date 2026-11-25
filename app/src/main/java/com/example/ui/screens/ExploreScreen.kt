package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.DietaryPreference
import com.example.data.model.DifficultyLevel
import com.example.data.model.ExploreContentType
import com.example.data.model.ExploreSortOrder
import com.example.data.model.FoodCategory
import com.example.data.sample.SampleCulinaryData
import com.example.ui.components.TrendingRecipeRowCard
import com.example.ui.components.VideoShowcaseCard
import com.example.ui.state.CookHubUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreScreen(
    uiState: CookHubUiState,
    onSearchQueryChange: (String) -> Unit,
    onSelectCategory: (FoodCategory) -> Unit,
    onSelectContentType: (ExploreContentType) -> Unit,
    onToggleDietary: (DietaryPreference) -> Unit,
    onSelectDifficulty: (DifficultyLevel?) -> Unit,
    onSelectSort: (ExploreSortOrder) -> Unit,
    onClearFilters: () -> Unit,
    onOpenVideoDetail: (String) -> Unit,
    onOpenCreatorProfile: (String) -> Unit,
    onToggleSaveVideo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAdvancedFilters by rememberSaveable { mutableStateOf(false) }
    val results = uiState.filteredExploreItems
    val activeFilterCount =
        (if (uiState.selectedCategory != FoodCategory.ALL) 1 else 0) +
            (if (uiState.contentType != ExploreContentType.ALL) 1 else 0) +
            uiState.selectedDietary.size +
            (if (uiState.selectedDifficulty != null) 1 else 0) +
            (if (uiState.searchQuery.isNotBlank()) 1 else 0)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Explore",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Search cooking videos, trending recipes, creators, and ingredients",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Live Search Bar
        item {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .testTag("explore_search_input"),
                placeholder = { Text("Search dishes, chefs, or ingredients…") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = { onSearchQueryChange("") },
                            modifier = Modifier.testTag("explore_clear_search")
                        ) {
                            Icon(Icons.Filled.Close, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )
        }

        // Quick Search Suggestion Tags when empty
        if (uiState.searchQuery.isBlank()) {
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(SampleCulinaryData.popularSearchTags) { tag ->
                        FilterChip(
                            selected = false,
                            onClick = { onSearchQueryChange(tag) },
                            label = { Text("#$tag", style = MaterialTheme.typography.labelMedium) },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                labelColor = MaterialTheme.colorScheme.onSurface
                            ),
                            border = null
                        )
                    }
                }
            }
        }

        // Content Type Switcher (All / Videos / Trending Recipes)
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ExploreContentType.entries) { type ->
                    val selected = uiState.contentType == type
                    FilterChip(
                        selected = selected,
                        onClick = { onSelectContentType(type) },
                        label = { Text(type.label) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                        ),
                        modifier = Modifier.testTag("explore_type_${type.name}")
                    )
                }
            }
        }

        // Category Filter Chips
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(FoodCategory.entries) { category ->
                    val selected = uiState.selectedCategory == category
                    FilterChip(
                        selected = selected,
                        onClick = { onSelectCategory(category) },
                        label = { Text(category.displayName) },
                        leadingIcon = if (selected) {
                            { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                        ),
                        modifier = Modifier.testTag("explore_cat_${category.name}")
                    )
                }
            }
        }

        // Results Summary + Advanced Filters Toggle
        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${results.size} ${if (results.size == 1) "result" else "results"}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (activeFilterCount > 0) {
                            TextButton(
                                onClick = onClearFilters,
                                modifier = Modifier.testTag("explore_reset_filters")
                            ) {
                                Text("Reset ($activeFilterCount)")
                            }
                        }
                        TextButton(
                            onClick = { showAdvancedFilters = !showAdvancedFilters },
                            modifier = Modifier.testTag("explore_toggle_filters")
                        ) {
                            Icon(Icons.Outlined.Tune, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text(if (showAdvancedFilters) "Hide Filters" else "Dietary & Level")
                        }
                    }
                }

                AnimatedVisibility(visible = showAdvancedFilters) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Dietary Preferences",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                DietaryPreference.entries.forEach { pref ->
                                    val selected = pref in uiState.selectedDietary
                                    FilterChip(
                                        selected = selected,
                                        onClick = { onToggleDietary(pref) },
                                        label = { Text(pref.displayName, style = MaterialTheme.typography.labelMedium) },
                                        leadingIcon = if (selected) {
                                            { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(14.dp)) }
                                        } else null,
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.tertiary,
                                            selectedLabelColor = MaterialTheme.colorScheme.onTertiary,
                                            selectedLeadingIconColor = MaterialTheme.colorScheme.onTertiary,
                                            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                                        )
                                    )
                                }
                            }

                            Text(
                                text = "Skill Level",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                DifficultyLevel.entries.forEach { level ->
                                    val selected = uiState.selectedDifficulty == level
                                    FilterChip(
                                        selected = selected,
                                        onClick = { onSelectDifficulty(level) },
                                        label = { Text(level.label, style = MaterialTheme.typography.labelMedium) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                // Sort Order Chips
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(ExploreSortOrder.entries) { order ->
                        FilterChip(
                            selected = uiState.sortOrder == order,
                            onClick = { onSelectSort(order) },
                            label = { Text(order.label, style = MaterialTheme.typography.labelMedium) }
                        )
                    }
                }
            }
        }

        // Results Feed or Empty State
        if (results.isEmpty()) {
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(
                        modifier = Modifier.padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.RestaurantMenu,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(44.dp)
                        )
                        Text(
                            text = "No matching cooking videos",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Try adjusting your search keywords, category, or dietary filters to uncover more recipes.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        Button(
                            onClick = onClearFilters,
                            modifier = Modifier.testTag("empty_state_clear_filters")
                        ) {
                            Text("Reset All Filters")
                        }
                    }
                }
            }
        } else {
            items(results, key = { it.id }) { item ->
                val creator = uiState.creators.find { it.id == item.creatorId }
                if (uiState.contentType == ExploreContentType.TRENDING_RECIPES) {
                    TrendingRecipeRowCard(
                        video = item,
                        creatorName = creator?.fullName ?: "CookHub Chef",
                        isSaved = item.id in uiState.savedVideoIds,
                        onClick = { onOpenVideoDetail(item.id) },
                        onToggleSave = { onToggleSaveVideo(item.id) },
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                } else {
                    VideoShowcaseCard(
                        video = item,
                        creator = creator,
                        isSaved = item.id in uiState.savedVideoIds,
                        onClick = { onOpenVideoDetail(item.id) },
                        onCreatorClick = { creator?.let { onOpenCreatorProfile(it.id) } },
                        onToggleSave = { onToggleSaveVideo(item.id) },
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}
