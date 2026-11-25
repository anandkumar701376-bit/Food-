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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CreatorDraftPreview
import com.example.ui.components.CreatorAvatarCircle
import com.example.ui.components.DishEditorialArtwork
import com.example.ui.components.FrontendPreviewBanner
import com.example.ui.components.MetricKpiCard
import com.example.ui.components.TrendingRecipeRowCard
import com.example.ui.state.CookHubUiState
import com.example.ui.theme.ApricotSoft
import com.example.ui.theme.SunsetOrange
import com.example.ui.theme.TangerineBright
import com.example.ui.theme.WarmOutline

@Composable
fun CreatorDashboardScreen(
    uiState: CookHubUiState,
    onSelectStudioCreator: (String) -> Unit,
    onOpenVideoDetail: (String) -> Unit,
    onOpenCreatorProfile: (String) -> Unit,
    onToggleSaveVideo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val creator = uiState.studioCreator
    val creatorVideos = uiState.videos.filter { it.creatorId == creator.id }
    val creatorDrafts = uiState.creatorDrafts.filter { it.creatorId == creator.id }

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
                    text = "Creator Dashboard",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Performance overview, published cooking videos, and recipe drafts",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Sample Creator Switcher
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.creators, key = { it.id }) { item ->
                    val selected = item.id == creator.id
                    FilterChip(
                        selected = selected,
                        onClick = { onSelectStudioCreator(item.id) },
                        label = { Text(item.fullName) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                        ),
                        modifier = Modifier.testTag("studio_creator_${item.id}")
                    )
                }
            }
        }

        // Active Creator Identity Strip
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CreatorAvatarCircle(creator = creator, sizeDp = 50)
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = creator.fullName,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${creator.handle} • ${creator.location}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    OutlinedButton(
                        onClick = { onOpenCreatorProfile(creator.id) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("studio_view_public_profile")
                    ) {
                        Text("Profile")
                        Spacer(Modifier.width(4.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(15.dp))
                    }
                }
            }
        }

        // 2x2 Analytics KPI Grid
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
                        label = "Total Views",
                        value = creator.totalViewsLabel,
                        badgeText = "+18%",
                        icon = Icons.Outlined.Visibility,
                        modifier = Modifier.weight(1f)
                    )
                    MetricKpiCard(
                        label = "Watch Hours",
                        value = creator.watchHoursLabel,
                        badgeText = "+12%",
                        icon = Icons.Outlined.Schedule,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MetricKpiCard(
                        label = "Followers",
                        value = creator.followersLabel,
                        badgeText = "+9%",
                        icon = Icons.Outlined.Group,
                        modifier = Modifier.weight(1f)
                    )
                    MetricKpiCard(
                        label = "Published",
                        value = "${creatorVideos.size} videos",
                        badgeText = "${creator.avgRating} ★",
                        icon = Icons.Outlined.PlayCircle,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Weekly Engagement Chart
        item {
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
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "7-Day Video Reach",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Sample viewer activity index (Mon–Sun)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = "Peak Sat",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    WeeklyReachBarChart(
                        values = creator.weeklyViews,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                            Text(
                                text = day,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Tabs: Published Videos / Drafts
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Published (${creatorVideos.size})") },
                    modifier = Modifier.testTag("studio_tab_published")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Drafts (${creatorDrafts.size})") },
                    modifier = Modifier.testTag("studio_tab_drafts")
                )
            }
        }

        if (selectedTab == 0) {
            items(creatorVideos, key = { it.id }) { video ->
                TrendingRecipeRowCard(
                    video = video,
                    creatorName = creator.fullName,
                    isSaved = video.id in uiState.savedVideoIds,
                    onClick = { onOpenVideoDetail(video.id) },
                    onToggleSave = { onToggleSaveVideo(video.id) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        } else {
            item {
                FrontendPreviewBanner(
                    title = "Video Uploads Not Enabled Yet",
                    description = "Showing sample draft cards for UI preview. Video uploads, cloud transcoding, and social publishing are intentionally excluded from this frontend-only build.",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            items(creatorDrafts, key = { it.id }) { draft ->
                CreatorDraftPreviewCard(
                    draft = draft,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    }
}

@Composable
private fun WeeklyReachBarChart(
    values: List<Int>,
    modifier: Modifier = Modifier
) {
    val maxVal = (values.maxOrNull() ?: 100).coerceAtLeast(1)
    Canvas(modifier = modifier) {
        val count = values.size.coerceAtLeast(1)
        val slotWidth = size.width / count
        val barWidth = slotWidth * 0.48f
        val chartHeight = size.height - 8.dp.toPx()

        // Subtle horizontal cream gridlines
        for (i in 0..3) {
            val y = chartHeight * (i / 3f)
            drawLine(
                color = WarmOutline.copy(alpha = 0.6f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )
        }

        values.forEachIndexed { idx, value ->
            val ratio = (value.toFloat() / maxVal.toFloat()).coerceIn(0.08f, 1f)
            val barH = chartHeight * ratio
            val left = idx * slotWidth + (slotWidth - barWidth) / 2f
            val top = chartHeight - barH
            val isPeak = value == maxVal

            // Soft cream track
            drawRoundRect(
                color = ApricotSoft.copy(alpha = 0.5f),
                topLeft = Offset(left, 0f),
                size = Size(barWidth, chartHeight),
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )

            // Warm orange gradient bar
            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = if (isPeak) {
                        listOf(TangerineBright, SunsetOrange)
                    } else {
                        listOf(TangerineBright.copy(alpha = 0.78f), SunsetOrange.copy(alpha = 0.85f))
                    }
                ),
                topLeft = Offset(left, top),
                size = Size(barWidth, barH),
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )
        }
    }
}

@Composable
private fun CreatorDraftPreviewCard(
    draft: CreatorDraftPreview,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                DishEditorialArtwork(style = draft.artworkStyle, modifier = Modifier.matchParentSize())
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = draft.stageLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = draft.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${draft.category.displayName} • ${draft.durationEstimate} • ${draft.lastUpdated}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
