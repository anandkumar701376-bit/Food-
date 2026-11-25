package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CookingVideoItem
import com.example.data.model.Creator
import com.example.ui.components.CreatorAvatarCircle
import com.example.ui.components.CreatorSpotlightCard
import com.example.ui.components.TrendingRecipeRowCard
import com.example.ui.components.VideoShowcaseCard
import com.example.ui.theme.BurntOrangeDeep
import com.example.ui.theme.PureIvory
import com.example.ui.theme.SunsetOrange
import com.example.ui.theme.TangerineBright

@Composable
fun CreatorProfileScreen(
    creator: Creator,
    creatorVideos: List<CookingVideoItem>,
    otherCreators: List<Creator>,
    allVideos: List<CookingVideoItem>,
    savedVideoIds: Set<String>,
    isFollowing: Boolean,
    onBack: () -> Unit,
    onToggleFollow: () -> Unit,
    onOpenVideoDetail: (String) -> Unit,
    onToggleSaveVideo: (String) -> Unit,
    onOpenOtherCreator: (String) -> Unit,
    onOpenCreatorDashboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember(creator.id) { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Editorial Warm Orange Banner + Back Button
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(142.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(creator.accentHex),
                                SunsetOrange,
                                TangerineBright.copy(alpha = 0.85f),
                                BurntOrangeDeep
                            )
                        )
                    )
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.35f))
                        .testTag("creator_profile_back")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PureIvory
                    )
                }

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    color = Color.Black.copy(alpha = 0.32f),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Creator Profile",
                        style = MaterialTheme.typography.labelMedium,
                        color = PureIvory,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Creator Identity Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        CreatorAvatarCircle(creator = creator, sizeDp = 68)
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = creator.fullName,
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                if (creator.isVerified) {
                                    Spacer(Modifier.width(5.dp))
                                    Icon(
                                        imageVector = Icons.Filled.Verified,
                                        contentDescription = "Verified Creator",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                            Text(
                                text = creator.handle,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = creator.location,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = creator.specialty,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                        )
                    }

                    Text(
                        text = creator.bio,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CreatorStatTile(creator.followersLabel, "Followers")
                        CreatorStatTile(creator.totalViewsLabel, "Views")
                        CreatorStatTile("${creatorVideos.size}", "Videos")
                        CreatorStatTile("${creator.avgRating} ★", "Rating")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onToggleFollow,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("creator_follow_button"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                imageVector = if (isFollowing) Icons.Filled.Check else Icons.Outlined.PersonAdd,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(if (isFollowing) "Following" else "Follow Chef")
                        }
                        OutlinedButton(
                            onClick = onOpenCreatorDashboard,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.testTag("creator_open_studio_button")
                        ) {
                            Icon(Icons.Outlined.Dashboard, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Studio")
                        }
                    }
                }
            }
        }

        // Videos / Recipes Tabs
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Videos (${creatorVideos.size})") },
                    modifier = Modifier.testTag("creator_tab_videos")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Recipes (${creatorVideos.size})") },
                    modifier = Modifier.testTag("creator_tab_recipes")
                )
            }
        }

        if (selectedTab == 0) {
            items(creatorVideos, key = { it.id }) { video ->
                VideoShowcaseCard(
                    video = video,
                    creator = creator,
                    isSaved = video.id in savedVideoIds,
                    onClick = { onOpenVideoDetail(video.id) },
                    onCreatorClick = {},
                    onToggleSave = { onToggleSaveVideo(video.id) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        } else {
            items(creatorVideos, key = { "rec_${it.id}" }) { video ->
                TrendingRecipeRowCard(
                    video = video,
                    creatorName = creator.fullName,
                    isSaved = video.id in savedVideoIds,
                    onClick = { onOpenVideoDetail(video.id) },
                    onToggleSave = { onToggleSaveVideo(video.id) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }

        // Discover More Creators
        if (otherCreators.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "More Creators on CookHub",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(otherCreators, key = { it.id }) { other ->
                            CreatorSpotlightCard(
                                creator = other,
                                videoCount = allVideos.count { it.creatorId == other.id },
                                onClick = { onOpenOtherCreator(other.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreatorStatTile(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
