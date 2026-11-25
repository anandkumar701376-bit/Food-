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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.model.CookingVideoItem
import com.example.data.model.Creator
import com.example.ui.components.CompactFeaturedVideoCard
import com.example.ui.components.CreatorAvatarCircle
import com.example.ui.components.DietaryTagFlowRow
import com.example.ui.components.DishEditorialArtwork
import com.example.ui.components.formatScaledQuantity
import com.example.ui.theme.EspressoDark
import com.example.ui.theme.PureIvory
import com.example.ui.theme.SaffronGold
import kotlinx.coroutines.delay

@Composable
fun VideoDetailScreen(
    video: CookingVideoItem,
    creator: Creator?,
    relatedVideos: List<CookingVideoItem>,
    allCreators: List<Creator>,
    isSaved: Boolean,
    isLiked: Boolean,
    isFollowingCreator: Boolean,
    onBack: () -> Unit,
    onToggleSave: () -> Unit,
    onToggleLike: () -> Unit,
    onToggleFollowCreator: () -> Unit,
    onOpenCreatorProfile: (String) -> Unit,
    onOpenRelatedVideo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedChapterIndex by remember(video.id) { mutableIntStateOf(0) }
    var isPlayingPreview by remember(video.id) { mutableStateOf(false) }
    var activeTab by remember(video.id) { mutableIntStateOf(0) }
    var servings by remember(video.id) { mutableIntStateOf(video.baseServings) }
    var checkedIngredients by remember(video.id) { mutableStateOf(emptySet<Int>()) }

    val scaleFactor = servings.toDouble() / video.baseServings.toDouble()
    val currentChapter = video.chapters.getOrNull(selectedChapterIndex)

    LaunchedEffect(isPlayingPreview, selectedChapterIndex, video.id) {
        if (isPlayingPreview && video.chapters.isNotEmpty()) {
            delay(3200)
            if (selectedChapterIndex < video.chapters.lastIndex) {
                selectedChapterIndex += 1
            } else {
                isPlayingPreview = false
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 36.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Video Storyboard Preview Stage
        item {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(256.dp)
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
                                    listOf(
                                        Color.Black.copy(alpha = 0.45f),
                                        Color.Black.copy(alpha = 0.15f),
                                        EspressoDark.copy(alpha = 0.90f)
                                    )
                                )
                            )
                    )

                    // Top Navigation & Actions
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(EspressoDark.copy(alpha = 0.68f))
                                .testTag("video_detail_back")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = PureIvory
                            )
                        }

                        Surface(
                            color = EspressoDark.copy(alpha = 0.78f),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = "PREVIEW • ${video.resolutionTag} • ${video.durationLabel}",
                                style = MaterialTheme.typography.labelSmall,
                                color = PureIvory,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        IconButton(
                            onClick = onToggleSave,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(EspressoDark.copy(alpha = 0.68f))
                                .testTag("video_detail_bookmark")
                        ) {
                            Icon(
                                imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                contentDescription = if (isSaved) "Remove bookmark" else "Save video",
                                tint = if (isSaved) SaffronGold else PureIvory
                            )
                        }
                    }

                    // Center Playback Controls (Storyboard Tour)
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                if (selectedChapterIndex > 0) selectedChapterIndex -= 1
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(EspressoDark.copy(alpha = 0.65f))
                                .testTag("video_prev_chapter")
                        ) {
                            Icon(Icons.Filled.SkipPrevious, contentDescription = "Previous chapter", tint = PureIvory)
                        }

                        Surface(
                            modifier = Modifier
                                .size(62.dp)
                                .clip(CircleShape)
                                .clickable { isPlayingPreview = !isPlayingPreview }
                                .testTag("video_play_pause"),
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            shadowElevation = 6.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (isPlayingPreview) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                    contentDescription = if (isPlayingPreview) "Pause preview" else "Play chapter preview",
                                    tint = PureIvory,
                                    modifier = Modifier.size(34.dp)
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                if (selectedChapterIndex < video.chapters.lastIndex) selectedChapterIndex += 1
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(EspressoDark.copy(alpha = 0.65f))
                                .testTag("video_next_chapter")
                        ) {
                            Icon(Icons.Filled.SkipNext, contentDescription = "Next chapter", tint = PureIvory)
                        }
                    }

                    // Active Chapter Subtitle Overlay
                    if (currentChapter != null) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            color = EspressoDark.copy(alpha = 0.85f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp)) {
                                Text(
                                    text = "${currentChapter.timestamp}  •  ${currentChapter.label}",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = PureIvory
                                )
                                Text(
                                    text = currentChapter.chefTip,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = PureIvory.copy(alpha = 0.86f),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }

                // Chapter Progress Scrubber
                if (video.chapters.isNotEmpty()) {
                    LinearProgressIndicator(
                        progress = { (selectedChapterIndex + 1).toFloat() / video.chapters.size.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }

        // Video Header Info & Actions
        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = video.category.displayName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Text(
                        text = "${video.viewsLabel} • ${video.publishedLabel}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = video.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = video.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (video.dietaryTags.isNotEmpty()) {
                    DietaryTagFlowRow(tags = video.dietaryTags)
                }

                // Like & Save Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val displayedLikes = video.likesCount + if (isLiked) 1 else 0
                    OutlinedButton(
                        onClick = onToggleLike,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("video_like_button"),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(
                            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Like video",
                            tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text("${displayedLikes / 1000}.${(displayedLikes % 1000) / 100}K Likes")
                    }

                    Button(
                        onClick = onToggleSave,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("video_save_button"),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Save recipe",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(if (isSaved) "Saved" else "Save Recipe")
                    }
                }
            }
        }

        // Prep / Cook / Rating Metadata Strip
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                VideoMetricBox("Prep", "${video.prepTimeMinutes}m", Icons.Outlined.Schedule, Modifier.weight(1f))
                VideoMetricBox("Cook", "${video.cookTimeMinutes}m", Icons.Outlined.LocalFireDepartment, Modifier.weight(1f))
                VideoMetricBox("Rating", "${video.rating} (${video.reviewCount})", Icons.Filled.Star, Modifier.weight(1f))
            }
        }

        // Creator Attribution Card
        if (creator != null) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable { onOpenCreatorProfile(creator.id) }
                        .testTag("video_creator_card"),
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
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = creator.fullName,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                if (creator.isVerified) {
                                    Spacer(Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.Filled.Verified,
                                        contentDescription = "Verified Creator",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Text(
                                text = "${creator.followersLabel} followers • ${creator.location}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        OutlinedButton(
                            onClick = onToggleFollowCreator,
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("video_follow_creator")
                        ) {
                            Icon(
                                imageVector = if (isFollowingCreator) Icons.Filled.Check else Icons.Outlined.PersonAdd,
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(if (isFollowingCreator) "Following" else "Follow", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }
        }

        // Chapters / Ingredients / Steps Tabs
        item {
            TabRow(
                selectedTabIndex = activeTab,
                containerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Tab(
                    selected = activeTab == 0,
                    onClick = { activeTab = 0 },
                    text = { Text("Chapters (${video.chapters.size})") },
                    modifier = Modifier.testTag("detail_tab_chapters")
                )
                Tab(
                    selected = activeTab == 1,
                    onClick = { activeTab = 1 },
                    text = { Text("Ingredients") },
                    modifier = Modifier.testTag("detail_tab_ingredients")
                )
                Tab(
                    selected = activeTab == 2,
                    onClick = { activeTab = 2 },
                    text = { Text("Steps (${video.steps.size})") },
                    modifier = Modifier.testTag("detail_tab_steps")
                )
            }
        }

        when (activeTab) {
            0 -> {
                items(video.chapters.size) { index ->
                    val chapter = video.chapters[index]
                    val selected = index == selectedChapterIndex
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                selectedChapterIndex = index
                                isPlayingPreview = false
                            }
                            .testTag("chapter_row_$index"),
                        shape = MaterialTheme.shapes.medium,
                        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainerLowest,
                        border = BorderStroke(
                            1.dp,
                            if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = chapter.timestamp,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (selected) PureIvory else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }
                            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = chapter.label,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = chapter.chefTip,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            1 -> {
                // Chef's Note + Servings Scaler
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Lightbulb,
                                    contentDescription = "Chef tip",
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.size(20.dp)
                                )
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text(
                                        text = "Chef's Technique Note",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = video.chefNote,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Adjust Servings",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = "${video.caloriesPerServing} kcal per serving",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { if (servings > 1) servings -= 1 },
                                        modifier = Modifier.testTag("servings_decrease")
                                    ) {
                                        Icon(Icons.Filled.Remove, contentDescription = "Decrease servings", modifier = Modifier.size(18.dp))
                                    }
                                    Text(
                                        text = "$servings servings",
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                    IconButton(
                                        onClick = { if (servings < 16) servings += 1 },
                                        modifier = Modifier.testTag("servings_increase")
                                    ) {
                                        Icon(Icons.Filled.Add, contentDescription = "Increase servings", modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                                video.ingredients.forEachIndexed { idx, ingredient ->
                                    val isChecked = idx in checkedIngredients
                                    val scaled = ingredient.quantity?.let { formatScaledQuantity(it * scaleFactor) }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                checkedIngredients = if (isChecked) checkedIngredients - idx else checkedIngredients + idx
                                            }
                                            .padding(horizontal = 12.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = isChecked,
                                            onCheckedChange = {
                                                checkedIngredients = if (isChecked) checkedIngredients - idx else checkedIngredients + idx
                                            },
                                            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                                        )
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = buildString {
                                                    if (scaled != null) append("$scaled ${ingredient.unit} ")
                                                    append(ingredient.name)
                                                },
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = FontWeight.Medium,
                                                textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None,
                                                color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                                            )
                                            if (ingredient.note.isNotBlank()) {
                                                Text(
                                                    text = ingredient.note,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else -> {
                items(video.steps, key = { it.stepNumber }) { step ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${step.stepNumber}",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = PureIvory,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = step.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                    step.videoTimestamp?.let { ts ->
                                        Surface(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable {
                                                    val idx = video.chapters.indexOfFirst { it.timestamp == ts }
                                                    if (idx >= 0) selectedChapterIndex = idx
                                                },
                                            color = MaterialTheme.colorScheme.primaryContainer,
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text(
                                                text = "▶ $ts",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = step.instruction,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                step.durationMinutes?.let { mins ->
                                    Text(
                                        text = "Est. time: $mins min",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Related Videos Strip
        if (relatedVideos.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Up Next on CookHub",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(relatedVideos, key = { it.id }) { related ->
                            val relCreator = allCreators.find { it.id == related.creatorId }?.fullName ?: "CookHub Chef"
                            CompactFeaturedVideoCard(
                                video = related,
                                creatorName = relCreator,
                                onClick = { onOpenRelatedVideo(related.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun VideoMetricBox(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainerLowest,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
