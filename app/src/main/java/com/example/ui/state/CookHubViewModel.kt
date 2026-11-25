package com.example.ui.state

import androidx.lifecycle.ViewModel
import com.example.data.model.AdminModerationItem
import com.example.data.model.CookingVideoItem
import com.example.data.model.Creator
import com.example.data.model.CreatorDraftPreview
import com.example.data.model.DietaryPreference
import com.example.data.model.DifficultyLevel
import com.example.data.model.ExploreContentType
import com.example.data.model.ExploreSortOrder
import com.example.data.model.FoodCategory
import com.example.data.model.ModerationStatus
import com.example.data.sample.SampleCulinaryData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CookHubUiState(
    val videos: List<CookingVideoItem> = SampleCulinaryData.videos,
    val creators: List<Creator> = SampleCulinaryData.creators,
    val creatorDrafts: List<CreatorDraftPreview> = SampleCulinaryData.creatorDrafts,
    val moderationItems: List<AdminModerationItem> = SampleCulinaryData.moderationItems,
    // Client-only UI toggles (no backend persistence)
    val savedVideoIds: Set<String> = setOf("vid_butter_chicken", "vid_mysore_dosa"),
    val likedVideoIds: Set<String> = setOf("vid_hyderabadi_biryani"),
    val followedCreatorIds: Set<String> = setOf("creator_ananya", "creator_zoya"),
    // Explore Filters
    val searchQuery: String = "",
    val selectedCategory: FoodCategory = FoodCategory.ALL,
    val contentType: ExploreContentType = ExploreContentType.ALL,
    val selectedDietary: Set<DietaryPreference> = emptySet(),
    val selectedDifficulty: DifficultyLevel? = null,
    val sortOrder: ExploreSortOrder = ExploreSortOrder.TRENDING,
    // Categories Screen Selection
    val spotlightCategory: FoodCategory = FoodCategory.COMFORT_CURRIES,
    // Creator Dashboard Selected Preview Creator
    val studioCreatorId: String = "creator_ananya",
    // Admin Dashboard Status Filter
    val adminStatusFilter: ModerationStatus? = null,
    // Transient UI Toast/Snackbar
    val snackbarMessage: String? = null
) {
    val featuredVideos: List<CookingVideoItem>
        get() = videos.filter { it.isFeatured }

    val trendingRecipes: List<CookingVideoItem>
        get() = videos.filter { it.isTrendingRecipe }

    val filteredExploreItems: List<CookingVideoItem>
        get() {
            val query = searchQuery.trim().lowercase()
            val matches = videos.filter { item ->
                val creatorName = creators.find { it.id == item.creatorId }?.fullName.orEmpty().lowercase()
                val categoryOk = selectedCategory == FoodCategory.ALL || item.category == selectedCategory
                val typeOk = when (contentType) {
                    ExploreContentType.ALL -> true
                    ExploreContentType.VIDEOS -> true
                    ExploreContentType.TRENDING_RECIPES -> item.isTrendingRecipe
                }
                val dietaryOk = selectedDietary.all { it in item.dietaryTags }
                val difficultyOk = selectedDifficulty == null || item.difficulty == selectedDifficulty
                val queryOk = query.isBlank() ||
                    item.title.lowercase().contains(query) ||
                    item.recipeName.lowercase().contains(query) ||
                    item.subtitle.lowercase().contains(query) ||
                    item.region.lowercase().contains(query) ||
                    item.category.displayName.lowercase().contains(query) ||
                    creatorName.contains(query) ||
                    item.ingredients.any { it.name.lowercase().contains(query) } ||
                    item.dietaryTags.any { it.displayName.lowercase().contains(query) }

                categoryOk && typeOk && dietaryOk && difficultyOk && queryOk
            }

            return when (sortOrder) {
                ExploreSortOrder.TRENDING -> matches.sortedByDescending { it.likesCount }
                ExploreSortOrder.TOP_RATED -> matches.sortedByDescending { it.rating }
                ExploreSortOrder.QUICKEST -> matches.sortedBy { it.totalTimeMinutes }
                ExploreSortOrder.MOST_VIEWED -> matches.sortedByDescending { it.viewsCount }
            }
        }

    val filteredModerationItems: List<AdminModerationItem>
        get() = if (adminStatusFilter == null) {
            moderationItems
        } else {
            moderationItems.filter { it.status == adminStatusFilter }
        }

    val pendingReviewCount: Int
        get() = moderationItems.count { it.status == ModerationStatus.PENDING }

    val studioCreator: Creator
        get() = creators.find { it.id == studioCreatorId } ?: creators.first()
}

class CookHubViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CookHubUiState())
    val uiState: StateFlow<CookHubUiState> = _uiState.asStateFlow()

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectCategory(category: FoodCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun selectContentType(type: ExploreContentType) {
        _uiState.update { it.copy(contentType = type) }
    }

    fun toggleDietary(pref: DietaryPreference) {
        _uiState.update { state ->
            val updated = if (pref in state.selectedDietary) {
                state.selectedDietary - pref
            } else {
                state.selectedDietary + pref
            }
            state.copy(selectedDietary = updated)
        }
    }

    fun selectDifficulty(level: DifficultyLevel?) {
        _uiState.update { state ->
            state.copy(selectedDifficulty = if (state.selectedDifficulty == level) null else level)
        }
    }

    fun selectSortOrder(order: ExploreSortOrder) {
        _uiState.update { it.copy(sortOrder = order) }
    }

    fun clearExploreFilters() {
        _uiState.update {
            it.copy(
                searchQuery = "",
                selectedCategory = FoodCategory.ALL,
                contentType = ExploreContentType.ALL,
                selectedDietary = emptySet(),
                selectedDifficulty = null,
                sortOrder = ExploreSortOrder.TRENDING
            )
        }
    }

    fun selectSpotlightCategory(category: FoodCategory) {
        _uiState.update { it.copy(spotlightCategory = category) }
    }

    fun toggleSaveVideo(videoId: String) {
        _uiState.update { state ->
            val saved = videoId in state.savedVideoIds
            state.copy(
                savedVideoIds = if (saved) state.savedVideoIds - videoId else state.savedVideoIds + videoId,
                snackbarMessage = if (saved) "Removed from saved recipes" else "Saved to your cookbook"
            )
        }
    }

    fun toggleLikeVideo(videoId: String) {
        _uiState.update { state ->
            val liked = videoId in state.likedVideoIds
            state.copy(
                likedVideoIds = if (liked) state.likedVideoIds - videoId else state.likedVideoIds + videoId
            )
        }
    }

    fun toggleFollowCreator(creatorId: String) {
        _uiState.update { state ->
            val following = creatorId in state.followedCreatorIds
            val name = state.creators.find { it.id == creatorId }?.fullName ?: "Creator"
            state.copy(
                followedCreatorIds = if (following) state.followedCreatorIds - creatorId else state.followedCreatorIds + creatorId,
                snackbarMessage = if (following) "Unfollowed $name" else "Following $name"
            )
        }
    }

    fun selectStudioCreator(creatorId: String) {
        _uiState.update { it.copy(studioCreatorId = creatorId) }
    }

    fun selectAdminStatusFilter(status: ModerationStatus?) {
        _uiState.update { it.copy(adminStatusFilter = status) }
    }

    fun postMessage(message: String) {
        _uiState.update { it.copy(snackbarMessage = message) }
    }

    fun clearMessage() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }

    fun findVideo(id: String): CookingVideoItem? = _uiState.value.videos.find { it.id == id }
    fun findCreator(id: String): Creator? = _uiState.value.creators.find { it.id == id }
}
