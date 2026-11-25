package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.model.CookingVideoItem
import com.example.data.model.FoodCategory
import com.example.ui.components.CompactFeaturedVideoCard
import com.example.ui.components.CookHubSectionHeader
import com.example.ui.components.CreatorSpotlightCard
import com.example.ui.components.DishEditorialArtwork
import com.example.ui.components.TrendingRecipeRowCard
import com.example.ui.state.CookHubUiState
import com.example.ui.theme.EspressoDark
import com.example.ui.theme.PureIvory
import com.example.ui.theme.SaffronGold

@Composable
fun HomeScreen(
    uiState: CookHubUiState,
    onOpenExplore: (FoodCategory?) -> Unit,
    onOpenCategories: () -> Unit,
    onOpenVideoDetail: (String) -> Unit,
    onOpenCreatorProfile: (String) -> Unit,
    onOpenCreatorDashboard: () -> Unit,
    onOpenAdminDashboard: () -> Unit,
    onOpenAuth: () -> Unit,
    onToggleSaveVideo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val heroVideo = uiState.featuredVideos.firstOrNull() ?: uiState.videos.first()
    val heroCreator = uiState.creators.find { it.id == heroVideo.creatorId }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        // Warm Editorial Masthead
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Filled.LocalFireDepartment,
                                contentDescription = "CookHub Logo",
                                tint = PureIvory,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "CookHub",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Modern Cooking Video Platform",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable(onClick = onOpenAuth)
                        .testTag("home_auth_button"),
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Login,
                            contentDescription = "Sign In",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Sign In",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }

        // Interactive Search Bar Launcher
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable { onOpenExplore(null) }
                    .testTag("home_search_launcher"),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search cooking videos and recipes",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Search Biryani, Dosa, Paneer, or chefs…",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Explore",
                            style = MaterialTheme.typography.labelSmall,
                            color = PureIvory,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }

        // Hero Featured Cooking Video Card
        item {
            HeroFeaturedVideoCard(
                video = heroVideo,
                creatorName = heroCreator?.fullName ?: "CookHub Chef",
                onWatchNow = { onOpenVideoDetail(heroVideo.id) },
                onBrowseCategories = onOpenCategories,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        // Quick Category Filter Pills
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                CookHubSectionHeader(
                    title = "Browse Categories",
                    actionLabel = "All Categories",
                    onAction = onOpenCategories
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(FoodCategory.entries.filter { it != FoodCategory.ALL }) { category ->
                        FilterChip(
                            selected = false,
                            onClick = { onOpenExplore(category) },
                            label = {
                                Text(
                                    text = category.displayName,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                                labelColor = MaterialTheme.colorScheme.onSurface
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = false,
                                borderColor = MaterialTheme.colorScheme.outline
                            ),
                            modifier = Modifier.testTag("home_cat_${category.name}")
                        )
                    }
                }
            }
        }

        // Featured Videos Carousel
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                CookHubSectionHeader(
                    title = "Featured Videos",
                    subtitle = "Hand-picked step-by-step culinary masterclasses",
                    actionLabel = "See All",
                    onAction = { onOpenExplore(null) }
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(uiState.featuredVideos, key = { it.id }) { video ->
                        val creator = uiState.creators.find { it.id == video.creatorId }
                        CompactFeaturedVideoCard(
                            video = video,
                            creatorName = creator?.fullName ?: "CookHub Chef",
                            onClick = { onOpenVideoDetail(video.id) }
                        )
                    }
                }
            }
        }

        // Top Creators Showcase
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                CookHubSectionHeader(
                    title = "Featured Creators",
                    subtitle = "Explore kitchens from top culinary storytellers",
                    actionLabel = "Creator Studio",
                    onAction = onOpenCreatorDashboard
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.creators, key = { it.id }) { creator ->
                        val count = uiState.videos.count { it.creatorId == creator.id }
                        CreatorSpotlightCard(
                            creator = creator,
                            videoCount = count,
                            onClick = { onOpenCreatorProfile(creator.id) }
                        )
                    }
                }
            }
        }

        // Trending Recipes List
        item {
            CookHubSectionHeader(
                title = "Trending Recipes",
                subtitle = "Most cooked this week with full ingredient scaling",
                actionLabel = "Explore All",
                onAction = { onOpenExplore(null) }
            )
        }

        items(uiState.trendingRecipes.take(4), key = { it.id }) { video ->
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

        // Platform Dashboards Quick Access
        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Platform Dashboards",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DashboardPortalTile(
                        title = "Creator Studio",
                        subtitle = "Analytics, videos & drafts",
                        icon = Icons.Outlined.Dashboard,
                        onClick = onOpenCreatorDashboard,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("home_creator_portal")
                    )
                    DashboardPortalTile(
                        title = "Admin Hub",
                        subtitle = "${uiState.pendingReviewCount} pending reviews",
                        icon = Icons.Outlined.AdminPanelSettings,
                        onClick = onOpenAdminDashboard,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("home_admin_portal")
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroFeaturedVideoCard(
    video: CookingVideoItem,
    creatorName: String,
    onWatchNow: () -> Unit,
    onBrowseCategories: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("home_hero_card")
            .clickable(onClick = onWatchNow),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(282.dp)
        ) {
            DishEditorialArtwork(
                style = video.artworkStyle,
                modifier = Modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.20f),
                                Color.Black.copy(alpha = 0.22f),
                                EspressoDark.copy(alpha = 0.92f)
                            )
                        )
                    )
            )

            // Top Badges
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "FEATURED • ${video.durationLabel}",
                        style = MaterialTheme.typography.labelSmall,
                        color = PureIvory,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }
                Surface(
                    color = EspressoDark.copy(alpha = 0.78f),
                    shape = RoundedCornerShape(50)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = SaffronGold,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${video.rating}",
                            style = MaterialTheme.typography.labelSmall,
                            color = PureIvory,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = PureIvory,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${video.subtitle} — by $creatorName",
                    style = MaterialTheme.typography.bodySmall,
                    color = PureIvory.copy(alpha = 0.88f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Button(
                        onClick = onWatchNow,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = PureIvory
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.testTag("hero_watch_now_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Watch Video")
                    }
                    OutlinedButton(
                        onClick = onBrowseCategories,
                        border = BorderStroke(1.dp, PureIvory.copy(alpha = 0.75f)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PureIvory),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.testTag("hero_categories_button")
                    ) {
                        Text("Categories")
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DashboardPortalTile(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape,
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
