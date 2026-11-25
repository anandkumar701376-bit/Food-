package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.AdminModerationItem
import com.example.data.model.FoodCategory
import com.example.data.model.ModerationStatus
import com.example.ui.components.CreatorAvatarCircle
import com.example.ui.components.DishEditorialArtwork
import com.example.ui.components.FrontendPreviewBanner
import com.example.ui.components.MetricKpiCard
import com.example.ui.components.ModerationStatusBadge
import com.example.ui.components.TrendingRecipeRowCard
import com.example.ui.state.CookHubUiState
import com.example.ui.theme.ApricotSoft
import com.example.ui.theme.SunsetOrange
import com.example.ui.theme.TangerineBright

@Composable
fun AdminDashboardScreen(
    uiState: CookHubUiState,
    onSelectStatusFilter: (ModerationStatus?) -> Unit,
    onOpenCreatorProfile: (String) -> Unit,
    onOpenVideoDetail: (String) -> Unit,
    onToggleSaveVideo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val categories = FoodCategory.entries.filter { it != FoodCategory.ALL }
    val categoryDistribution = categories.map { cat ->
        cat to uiState.videos.count { it.category == cat }
    }.sortedByDescending { it.second }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Admin Dashboard",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Platform catalog overview, creator directory, and review queue UI",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            FrontendPreviewBanner(
                title = "Frontend Admin Preview",
                description = "Displays sample platform analytics and moderation queue data. Backend persistence and live moderation actions are not connected yet.",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        // 2x2 Platform KPI Grid
        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricKpiCard(
                        label = "Total Videos",
                        value = "${uiState.videos.size}",
                        badgeText = "Sample",
                        icon = Icons.Outlined.PlayCircle,
                        modifier = Modifier.weight(1f)
                    )
                    MetricKpiCard(
                        label = "Creators",
                        value = "${uiState.creators.size}",
                        badgeText = "Verified",
                        icon = Icons.Outlined.Group,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricKpiCard(
                        label = "Categories",
                        value = "${categories.size}",
                        badgeText = "Active",
                        icon = Icons.Outlined.Category,
                        modifier = Modifier.weight(1f)
                    )
                    MetricKpiCard(
                        label = "Pending Reviews",
                        value = "${uiState.pendingReviewCount}",
                        badgeText = "Queue",
                        icon = Icons.Outlined.PendingActions,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Category Distribution Chart
        item {
            val maxCount = (categoryDistribution.maxOfOrNull { it.second } ?: 1).coerceAtLeast(1)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Catalog by Category",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    categoryDistribution.take(5).forEach { (cat, count) ->
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = cat.displayName,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "$count ${if (count == 1) "video" else "videos"}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            CategoryDistributionBar(
                                fraction = count.toFloat() / maxCount.toFloat(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                            )
                        }
                    }
                }
            }
        }

        // Tabs: Review Queue / Creators / Catalog
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Queue (${uiState.moderationItems.size})") },
                    modifier = Modifier.testTag("admin_tab_queue")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Creators") },
                    modifier = Modifier.testTag("admin_tab_creators")
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Catalog") },
                    modifier = Modifier.testTag("admin_tab_catalog")
                )
            }
        }

        when (selectedTab) {
            0 -> {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            FilterChip(
                                selected = uiState.adminStatusFilter == null,
                                onClick = { onSelectStatusFilter(null) },
                                label = { Text("All (${uiState.moderationItems.size})") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier.testTag("admin_filter_all")
                            )
                        }
                        items(ModerationStatus.entries) { status ->
                            FilterChip(
                                selected = uiState.adminStatusFilter == status,
                                onClick = { onSelectStatusFilter(status) },
                                label = { Text(status.label) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier.testTag("admin_filter_${status.name}")
                            )
                        }
                    }
                }

                items(uiState.filteredModerationItems, key = { it.id }) { item ->
                    AdminQueuePreviewCard(
                        item = item,
                        onOpenCreator = { onOpenCreatorProfile(item.creatorId) },
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }

            1 -> {
                items(uiState.creators, key = { it.id }) { creator ->
                    val videoCount = uiState.videos.count { it.creatorId == creator.id }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .clickable { onOpenCreatorProfile(creator.id) }
                            .testTag("admin_creator_${creator.id}"),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CreatorAvatarCircle(creator = creator, sizeDp = 48)
                            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = creator.fullName,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    if (creator.isVerified) {
                                        Spacer(Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.Filled.Verified,
                                            contentDescription = "Verified",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "${creator.handle} • ${creator.followersLabel} followers • $videoCount videos",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = "View →",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            else -> {
                items(uiState.videos, key = { it.id }) { video ->
                    val creatorName = uiState.creators.find { it.id == video.creatorId }?.fullName ?: "CookHub Chef"
                    TrendingRecipeRowCard(
                        video = video,
                        creatorName = creatorName,
                        isSaved = video.id in uiState.savedVideoIds,
                        onClick = { onOpenVideoDetail(video.id) },
                        onToggleSave = { onToggleSaveVideo(video.id) },
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryDistributionBar(
    fraction: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val radius = size.height / 2f
        drawRoundRect(
            color = ApricotSoft,
            size = size,
            cornerRadius = CornerRadius(radius, radius)
        )
        drawRoundRect(
            brush = Brush.horizontalGradient(listOf(TangerineBright, SunsetOrange)),
            size = Size(size.width * fraction.coerceIn(0.08f, 1f), size.height),
            cornerRadius = CornerRadius(radius, radius)
        )
    }
}

@Composable
private fun AdminQueuePreviewCard(
    item: AdminModerationItem,
    onOpenCreator: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("admin_queue_${item.id}"),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(66.dp)
                        .clip(MaterialTheme.shapes.medium)
                ) {
                    DishEditorialArtwork(style = item.artworkStyle, modifier = Modifier.matchParentSize())
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.category.displayName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        ModerationStatusBadge(item.status)
                    }
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${item.creatorName} (${item.creatorHandle}) • ${item.durationLabel} • ${item.submittedAt}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable(onClick = onOpenCreator)
                    )
                }
            }

            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = item.summaryNote,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
