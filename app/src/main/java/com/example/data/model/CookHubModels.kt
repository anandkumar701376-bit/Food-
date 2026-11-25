package com.example.data.model

import androidx.compose.runtime.Immutable

enum class FoodCategory(
    val displayName: String,
    val tagline: String,
    val accentHex: Long,
    val artworkStyle: DishArtworkStyle
) {
    ALL(
        displayName = "All",
        tagline = "Every featured video and recipe",
        accentHex = 0xFFEA580C,
        artworkStyle = DishArtworkStyle.BUTTER_CHICKEN_HANDI
    ),
    COMFORT_CURRIES(
        displayName = "Rich Curries",
        tagline = "Slow-simmered gravies & aromatic masalas",
        accentHex = 0xFFD95B26,
        artworkStyle = DishArtworkStyle.BUTTER_CHICKEN_HANDI
    ),
    QUICK_WEEKNIGHT(
        displayName = "Quick Meals",
        tagline = "Flavor-packed bowls in under 30 minutes",
        accentHex = 0xFFE67E22,
        artworkStyle = DishArtworkStyle.PALAK_PANEER_BOWL
    ),
    BIRYANI_RICE(
        displayName = "Rice & Biryani",
        tagline = "Layered saffron grains & fragrant birista",
        accentHex = 0xFFD97706,
        artworkStyle = DishArtworkStyle.HYDERABADI_BIRYANI_POT
    ),
    STREET_FOOD(
        displayName = "Street Food",
        tagline = "Sizzling tawa bites & crispy savory classics",
        accentHex = 0xFFEA580C,
        artworkStyle = DishArtworkStyle.MUMBAI_PAV_BHAJI
    ),
    GRILL_ROAST(
        displayName = "Grill & Tandoor",
        tagline = "Smoky charred skewers & blistered crusts",
        accentHex = 0xFFC2410C,
        artworkStyle = DishArtworkStyle.PANEER_TIKKA_PLATTER
    ),
    SOUTH_COASTAL(
        displayName = "Coastal & Dosa",
        tagline = "Crisp fermented crepes & silky coconut stews",
        accentHex = 0xFF2E7D4F,
        artworkStyle = DishArtworkStyle.MASALA_DOSA_THALI
    ),
    SWEETS_BAKING(
        displayName = "Sweets & Baking",
        tagline = "Saffron puddings, warm pastries & festive treats",
        accentHex = 0xFFB45309,
        artworkStyle = DishArtworkStyle.KESAR_RASMALAI_BOWL
    )
}

enum class DietaryPreference(val displayName: String) {
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    GLUTEN_FREE("Gluten-Free"),
    HIGH_PROTEIN("High-Protein"),
    DAIRY_FREE("Dairy-Free"),
    NUT_FREE("Nut-Free")
}

enum class DifficultyLevel(val label: String) {
    EASY("Easy"),
    MEDIUM("Medium"),
    CHEF("Chef Craft")
}

enum class DishArtworkStyle {
    BUTTER_CHICKEN_HANDI,
    MASALA_DOSA_THALI,
    HYDERABADI_BIRYANI_POT,
    PANEER_TIKKA_PLATTER,
    CHOLE_BHATURE_FEAST,
    MALABAR_FISH_CURRY,
    KESAR_RASMALAI_BOWL,
    DAL_BAATI_CHURMA,
    MUMBAI_PAV_BHAJI,
    PALAK_PANEER_BOWL
}

enum class ExploreContentType(val label: String) {
    ALL("All"),
    VIDEOS("Videos"),
    TRENDING_RECIPES("Trending Recipes")
}

enum class ExploreSortOrder(val label: String) {
    TRENDING("Trending"),
    TOP_RATED("Top Rated"),
    QUICKEST("Quickest"),
    MOST_VIEWED("Most Viewed")
}

enum class ModerationStatus(val label: String) {
    PENDING("Pending Review"),
    APPROVED("Approved"),
    NEEDS_EDITS("Needs Edits")
}

@Immutable
data class Ingredient(
    val name: String,
    val quantity: Double?,
    val unit: String,
    val note: String = ""
)

@Immutable
data class CookingStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val durationMinutes: Int? = null,
    val videoTimestamp: String? = null
)

@Immutable
data class VideoChapter(
    val timestamp: String,
    val label: String,
    val chefTip: String
)

@Immutable
data class Creator(
    val id: String,
    val fullName: String,
    val handle: String,
    val location: String,
    val specialty: String,
    val bio: String,
    val followersLabel: String,
    val totalViewsLabel: String,
    val watchHoursLabel: String,
    val avgRating: Double,
    val isVerified: Boolean,
    val avatarInitials: String,
    val accentHex: Long,
    val weeklyViews: List<Int>
)

@Immutable
data class CookingVideoItem(
    val id: String,
    val title: String,
    val recipeName: String,
    val subtitle: String,
    val region: String,
    val category: FoodCategory,
    val dietaryTags: List<DietaryPreference>,
    val difficulty: DifficultyLevel,
    val durationLabel: String,
    val viewsCount: Int,
    val viewsLabel: String,
    val likesCount: Int,
    val publishedLabel: String,
    val resolutionTag: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val baseServings: Int,
    val caloriesPerServing: Int,
    val rating: Double,
    val reviewCount: Int,
    val creatorId: String,
    val artworkStyle: DishArtworkStyle,
    val isFeatured: Boolean = false,
    val isTrendingRecipe: Boolean = false,
    val chefNote: String,
    val chapters: List<VideoChapter>,
    val ingredients: List<Ingredient>,
    val steps: List<CookingStep>
) {
    val totalTimeMinutes: Int get() = prepTimeMinutes + cookTimeMinutes
}

@Immutable
data class CreatorDraftPreview(
    val id: String,
    val title: String,
    val creatorId: String,
    val category: FoodCategory,
    val durationEstimate: String,
    val stageLabel: String,
    val lastUpdated: String,
    val artworkStyle: DishArtworkStyle
)

@Immutable
data class AdminModerationItem(
    val id: String,
    val title: String,
    val creatorId: String,
    val creatorName: String,
    val creatorHandle: String,
    val category: FoodCategory,
    val durationLabel: String,
    val submittedAt: String,
    val status: ModerationStatus,
    val summaryNote: String,
    val artworkStyle: DishArtworkStyle
)
