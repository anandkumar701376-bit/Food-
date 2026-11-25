package com.example.data.sample

import com.example.data.model.AdminModerationItem
import com.example.data.model.CookingStep
import com.example.data.model.CookingVideoItem
import com.example.data.model.Creator
import com.example.data.model.CreatorDraftPreview
import com.example.data.model.DietaryPreference
import com.example.data.model.DifficultyLevel
import com.example.data.model.DishArtworkStyle
import com.example.data.model.FoodCategory
import com.example.data.model.Ingredient
import com.example.data.model.ModerationStatus
import com.example.data.model.VideoChapter

object SampleCulinaryData {

    val popularSearchTags: List<String> = listOf(
        "Paneer",
        "Biryani",
        "Dosa",
        "30-Min Meals",
        "Vegan",
        "Saffron",
        "Street Food",
        "High-Protein"
    )

    val creators: List<Creator> = listOf(
        Creator(
            id = "creator_ananya",
            fullName = "Chef Ananya Verma",
            handle = "@ananyaskitchen",
            location = "New Delhi, IN",
            specialty = "Slow-Simmered Curries & Clay-Pot Classics",
            bio = "Bringing heirloom spice techniques, velvety gravies, and weekend feasts to modern home kitchens with clear, step-by-step video lessons.",
            followersLabel = "148K",
            totalViewsLabel = "4.2M",
            watchHoursLabel = "186K hrs",
            avgRating = 4.9,
            isVerified = true,
            avatarInitials = "AV",
            accentHex = 0xFFEA580C,
            weeklyViews = listOf(42, 58, 51, 74, 89, 96, 84)
        ),
        Creator(
            id = "creator_karthik",
            fullName = "Karthik Iyer",
            handle = "@tiffinwithkarthik",
            location = "Bengaluru, IN",
            specialty = "Fermented Batters, Crisp Dosas & Coastal Stews",
            bio = "Focused on crisp golden dosa acoustics, stone-ground chutneys, and fragrant coconut curries you can master on a regular stove.",
            followersLabel = "96.4K",
            totalViewsLabel = "2.7M",
            watchHoursLabel = "112K hrs",
            avgRating = 4.9,
            isVerified = true,
            avatarInitials = "KI",
            accentHex = 0xFF2E7D4F,
            weeklyViews = listOf(28, 36, 44, 41, 63, 72, 68)
        ),
        Creator(
            id = "creator_zoya",
            fullName = "Zoya Qureshi",
            handle = "@dumbyzoya",
            location = "Hyderabad, IN",
            specialty = "Layered Dum Biryani & Saffron Desserts",
            bio = "Third-generation home chef sharing foolproof dum sealing, crispy golden birista, and celebration desserts steeped in saffron.",
            followersLabel = "214K",
            totalViewsLabel = "6.8M",
            watchHoursLabel = "294K hrs",
            avgRating = 5.0,
            isVerified = true,
            avatarInitials = "ZQ",
            accentHex = 0xFFD97706,
            weeklyViews = listOf(64, 71, 68, 85, 92, 100, 94)
        ),
        Creator(
            id = "creator_rohan",
            fullName = "Rohan Deshmukh",
            handle = "@mumbaistreetcraft",
            location = "Mumbai, IN",
            specialty = "Tawa Street Food & Fast Weeknight Bowls",
            bio = "Fast, bold street-style flavors—from sizzling butter pav bhaji to vibrant 25-minute greens and weeknight skillet favorites.",
            followersLabel = "82.1K",
            totalViewsLabel = "2.1M",
            watchHoursLabel = "89K hrs",
            avgRating = 4.8,
            isVerified = true,
            avatarInitials = "RD",
            accentHex = 0xFFC2410C,
            weeklyViews = listOf(31, 45, 39, 52, 66, 78, 70)
        )
    )

    val videos: List<CookingVideoItem> = listOf(
        CookingVideoItem(
            id = "vid_butter_chicken",
            title = "Old Delhi Murgh Makhani: Silky Tomato-Butter Gravy",
            recipeName = "Old Delhi Murgh Makhani",
            subtitle = "Charred spiced chicken simmered in velvety cashew-tomato gravy with toasted kasuri methi",
            region = "New Delhi",
            category = FoodCategory.COMFORT_CURRIES,
            dietaryTags = listOf(DietaryPreference.GLUTEN_FREE, DietaryPreference.HIGH_PROTEIN),
            difficulty = DifficultyLevel.MEDIUM,
            durationLabel = "09:14",
            viewsCount = 184200,
            viewsLabel = "184K views",
            likesCount = 14800,
            publishedLabel = "2 days ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 25,
            cookTimeMinutes = 35,
            baseServings = 4,
            caloriesPerServing = 490,
            rating = 4.9,
            reviewCount = 418,
            creatorId = "creator_ananya",
            artworkStyle = DishArtworkStyle.BUTTER_CHICKEN_HANDI,
            isFeatured = true,
            isTrendingRecipe = true,
            chefNote = "Pass the blended tomato-cashew sauce through a fine mesh strainer—that single step gives restaurant-level silkiness.",
            chapters = listOf(
                VideoChapter("00:35", "Hung Curd & Kashmiri Chili Marinade", "Strain yogurt for 2 hours so spices cling tightly without watering down."),
                VideoChapter("02:18", "High-Heat Cast-Iron Blistering", "Wait until oil shimmers before placing chicken; never crowd the pan."),
                VideoChapter("04:40", "Simmering Tomato-Cashew Base", "Whole green cardamom pods perfume the gravy gently."),
                VideoChapter("07:50", "Butter, Cream & Kasuri Methi Finish", "Rub warm dried fenugreek leaves between your palms right before serving.")
            ),
            ingredients = listOf(
                Ingredient("Boneless chicken thighs", 700.0, "g", "cut into 2-inch pieces"),
                Ingredient("Ripe Roma tomatoes", 600.0, "g", "roughly chopped"),
                Ingredient("Raw cashews", 40.0, "g", "soaked in warm water"),
                Ingredient("Cultured unsalted butter", 60.0, "g", "divided"),
                Ingredient("Kashmiri chili powder", 2.0, "tbsp", "vibrant color, mild heat"),
                Ingredient("Ginger-garlic paste", 2.0, "tbsp", "freshly pounded"),
                Ingredient("Thick hung yogurt", 100.0, "g", "for marinade"),
                Ingredient("Kasuri methi (dried fenugreek)", 1.5, "tbsp", "toasted & crushed"),
                Ingredient("Garam masala", 1.0, "tsp", "aromatic finish"),
                Ingredient("Heavy cream", 60.0, "ml", "for velvety swirl")
            ),
            steps = listOf(
                CookingStep(1, "Marinate & Rest", "Whisk hung yogurt, half the Kashmiri chili, half the ginger-garlic paste, lemon juice, and salt. Coat chicken and rest 25 minutes.", 25, "00:35"),
                CookingStep(2, "Char on Cast Iron", "Sear marinated chicken on a smoking hot cast-iron pan with a brush of oil until deep smoky blisters appear.", 10, "02:18"),
                CookingStep(3, "Simmer Makhani Base", "Sauté tomatoes, soaked cashews, green cardamom, ginger-garlic, and Kashmiri chili in half the butter until jammy.", 15, "04:40"),
                CookingStep(4, "Blend & Strain Silky", "Blend the cooled tomato mixture completely smooth, then pass through a fine strainer back into the pan.", 5, "06:15"),
                CookingStep(5, "Finish with Butter & Methi", "Fold in charred chicken, remaining butter, crushed kasuri methi, garam masala, and cream. Simmer 8 minutes.", 8, "07:50")
            )
        ),
        CookingVideoItem(
            id = "vid_hyderabadi_biryani",
            title = "Royal Hyderabadi Dum Biryani from Scratch",
            recipeName = "Royal Hyderabadi Dum Biryani",
            subtitle = "Saffron-streaked aged basmati rice sealed over spiced marinated layers and crispy golden birista",
            region = "Hyderabad",
            category = FoodCategory.BIRYANI_RICE,
            dietaryTags = listOf(DietaryPreference.GLUTEN_FREE, DietaryPreference.HIGH_PROTEIN),
            difficulty = DifficultyLevel.CHEF,
            durationLabel = "12:40",
            viewsCount = 318500,
            viewsLabel = "318K views",
            likesCount = 29400,
            publishedLabel = "5 days ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 40,
            cookTimeMinutes = 50,
            baseServings = 6,
            caloriesPerServing = 560,
            rating = 5.0,
            reviewCount = 528,
            creatorId = "creator_zoya",
            artworkStyle = DishArtworkStyle.HYDERABADI_BIRYANI_POT,
            isFeatured = true,
            isTrendingRecipe = true,
            chefNote = "Parboil soaked basmati rice to 70% doneness—when pressed between your fingers, a grain should snap cleanly into three pieces.",
            chapters = listOf(
                VideoChapter("00:50", "Crispy Golden Birista", "Lift onions out one shade early—they darken rapidly as they cool."),
                VideoChapter("02:40", "Layering the Kacchi Marinade", "Crush half the crisp birista directly into the spiced yogurt."),
                VideoChapter("05:05", "The 70% Rice Snap Test", "Drain immediately once grains elongate and snap cleanly."),
                VideoChapter("07:20", "Saffron Milk & Dum Seal", "Rest the pot 15 minutes off heat before opening so steam settles.")
            ),
            ingredients = listOf(
                Ingredient("Aged extra-long basmati rice", 500.0, "g", "washed & soaked 45 mins"),
                Ingredient("Saffron strands", 0.5, "tsp", "steeped in warm milk"),
                Ingredient("Crispy fried onions (birista)", 180.0, "g", "thinly sliced & caramelized"),
                Ingredient("Chicken or tender jackfruit", 750.0, "g", "for kacchi layer"),
                Ingredient("Fresh mint & cilantro leaves", 80.0, "g", "chopped"),
                Ingredient("Shahi jeera & green cardamom", 1.5, "tbsp", "whole aromatics"),
                Ingredient("Thick whisked yogurt", 150.0, "g", "room temperature"),
                Ingredient("Pure desi ghee", 4.0, "tbsp", "for fragrant layering")
            ),
            steps = listOf(
                CookingStep(1, "Crisp the Birista", "Slow-fry thinly sliced red onions until deep golden bronze, then drain on paper towels so they stay crisp.", 15, "00:50"),
                CookingStep(2, "Infuse Marinade", "Combine protein with yogurt, crushed birista, ginger-garlic paste, mint, saffron milk, and freshly ground garam masala.", 10, "02:40"),
                CookingStep(3, "Parboil Basmati", "Boil soaked rice in generously salted water with bay leaf, cardamom, and shahi jeera until 70% cooked.", 8, "05:05"),
                CookingStep(4, "Layer & Seal for Dum", "Layer rice over marinade in a heavy pot, drizzle saffron milk, ghee, and birista. Seal tightly.", 7, "07:20"),
                CookingStep(5, "Slow Steam & Rest", "Cook 10 minutes on medium heat, then 25 minutes on lowest heat over a tawa. Rest 15 minutes before fluffing.", 35, "09:45")
            )
        ),
        CookingVideoItem(
            id = "vid_mysore_dosa",
            title = "Crisp Mysore Masala Dosa & Fiery Red Chutney",
            recipeName = "Crispy Mysore Masala Dosa",
            subtitle = "Lacy golden fermented rice-lentil crepe layered with spiced red garlic chutney and potato masala",
            region = "Mysuru",
            category = FoodCategory.SOUTH_COASTAL,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN, DietaryPreference.GLUTEN_FREE, DietaryPreference.NUT_FREE),
            difficulty = DifficultyLevel.MEDIUM,
            durationLabel = "08:05",
            viewsCount = 142700,
            viewsLabel = "142K views",
            likesCount = 11900,
            publishedLabel = "4 days ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 20,
            cookTimeMinutes = 25,
            baseServings = 4,
            caloriesPerServing = 340,
            rating = 4.9,
            reviewCount = 312,
            creatorId = "creator_karthik",
            artworkStyle = DishArtworkStyle.MASALA_DOSA_THALI,
            isFeatured = true,
            isTrendingRecipe = true,
            chefNote = "Splash water onto the seasoned cast-iron tawa and wipe it right before swirling the batter for an even, crisp amber crust.",
            chapters = listOf(
                VideoChapter("00:45", "Byadagi Chili Garlic Chutney", "Soak dried chilies in warm water 15 minutes for vivid crimson paste."),
                VideoChapter("02:30", "Silky Potato Bhaji Tempering", "A splash of warm water keeps potato masala scoopable and lush."),
                VideoChapter("04:15", "Tawa Temperature Control", "Splash and wipe before every single crepe."),
                VideoChapter("05:40", "Swirl, Ghee Crisp & Fold", "Wait for edges to lift naturally before folding.")
            ),
            ingredients = listOf(
                Ingredient("Fermented dosa batter", 500.0, "ml", "rice & urad dal, room temp"),
                Ingredient("Yukon gold potatoes", 450.0, "g", "boiled & roughly mashed"),
                Ingredient("Fresh curry leaves", 20.0, "leaves", "fragrant tempering"),
                Ingredient("Byadagi dried red chilies", 6.0, "pcs", "soaked for red chutney"),
                Ingredient("Roasted chana dal", 3.0, "tbsp", "for chutney body"),
                Ingredient("Black mustard seeds", 1.0, "tsp", "crackled in oil"),
                Ingredient("Fresh grated coconut", 60.0, "g", "for cooling chutney"),
                Ingredient("Ghee or cold-pressed oil", 3.0, "tbsp", "for crisp edges")
            ),
            steps = listOf(
                CookingStep(1, "Grind Mysore Red Chutney", "Blend soaked Byadagi chilies, roasted chana dal, garlic, tamarind, and salt into a smooth red spread.", 6, "00:45"),
                CookingStep(2, "Temper Potato Bhaji", "Crackle mustard seeds, chana dal, curry leaves, green chilies, and sliced onions. Fold in turmeric and potatoes.", 10, "02:30"),
                CookingStep(3, "Season & Cool Tawa", "Heat cast-iron tawa, splash water, and wipe clean with a cotton towel.", 2, "04:15"),
                CookingStep(4, "Swirl Thin & Layer", "Ladle batter in the center, spiral outward swiftly, drizzle ghee, spread red chutney, and add potato bhaji.", 5, "05:40"),
                CookingStep(5, "Fold Golden & Serve", "Fold once deep amber and crisp. Serve hot with coconut chutney and sambar.", 2, "07:10")
            )
        ),
        CookingVideoItem(
            id = "vid_paneer_tikka",
            title = "No-Tandoor Smoky Paneer Tikka in 20 Minutes",
            recipeName = "Smoky Tandoori Paneer Tikka",
            subtitle = "Charred malai paneer cubes coated in roasted besan, ajwain, and hung yogurt with mint chutney",
            region = "Amritsar",
            category = FoodCategory.GRILL_ROAST,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN, DietaryPreference.GLUTEN_FREE, DietaryPreference.HIGH_PROTEIN, DietaryPreference.NUT_FREE),
            difficulty = DifficultyLevel.EASY,
            durationLabel = "06:22",
            viewsCount = 96400,
            viewsLabel = "96K views",
            likesCount = 8750,
            publishedLabel = "6 days ago",
            resolutionTag = "1080p HD",
            prepTimeMinutes = 15,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 320,
            rating = 4.8,
            reviewCount = 245,
            creatorId = "creator_ananya",
            artworkStyle = DishArtworkStyle.PANEER_TIKKA_PLATTER,
            isFeatured = true,
            isTrendingRecipe = true,
            chefNote = "Blooming Kashmiri chili in warm smoked mustard oil yields a deep fiery orange glaze naturally with zero food coloring.",
            chapters = listOf(
                VideoChapter("00:40", "Smoked Mustard Oil Marinade", "Cool oil 2 minutes before whisking in chili so it blooms gently."),
                VideoChapter("02:15", "Coating Soft Malai Paneer", "Toss gently with fingertips so paneer cubes stay whole."),
                VideoChapter("04:10", "Broiler Char & Butter Baste", "Brush melted butter halfway through for glossy charred edges.")
            ),
            ingredients = listOf(
                Ingredient("Fresh malai paneer", 400.0, "g", "cut into thick cubes"),
                Ingredient("Bell peppers & red onion", 250.0, "g", "cut into petals"),
                Ingredient("Hung curd (strained yogurt)", 120.0, "g", "thick & creamy"),
                Ingredient("Roasted besan (gram flour)", 2.0, "tbsp", "binds the marinade"),
                Ingredient("Mustard oil", 2.0, "tbsp", "smoked & cooled"),
                Ingredient("Kashmiri chili & chaat masala", 2.0, "tsp", "smoky tang"),
                Ingredient("Fresh mint & cilantro", 60.0, "g", "for chutney")
            ),
            steps = listOf(
                CookingStep(1, "Bloom Marinade", "Whisk warm smoked mustard oil with Kashmiri chili, roasted besan, hung curd, ginger-garlic, and ajwain.", 8, "00:40"),
                CookingStep(2, "Coat Paneer & Peppers", "Fold paneer cubes and bell pepper petals gently into the vibrant marinade.", 7, "02:15"),
                CookingStep(3, "Skewer & High-Heat Roast", "Thread onto skewers and broil at high heat, basting with butter until edges char.", 12, "04:10"),
                CookingStep(4, "Chaat Finish", "Dust with chaat masala and lime juice. Serve sizzling with emerald mint chutney.", 3, "05:30")
            )
        ),
        CookingVideoItem(
            id = "vid_pav_bhaji",
            title = "Mumbai Chowpatty Tawa Pav Bhaji",
            recipeName = "Mumbai Street Pav Bhaji",
            subtitle = "Sizzling spiced vegetable mash finished with butter and toasted masala-brushed ladi pav",
            region = "Mumbai",
            category = FoodCategory.STREET_FOOD,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN, DietaryPreference.NUT_FREE),
            difficulty = DifficultyLevel.EASY,
            durationLabel = "05:48",
            viewsCount = 163900,
            viewsLabel = "164K views",
            likesCount = 15200,
            publishedLabel = "3 days ago",
            resolutionTag = "1080p HD",
            prepTimeMinutes = 12,
            cookTimeMinutes = 18,
            baseServings = 4,
            caloriesPerServing = 430,
            rating = 4.9,
            reviewCount = 340,
            creatorId = "creator_rohan",
            artworkStyle = DishArtworkStyle.MUMBAI_PAV_BHAJI,
            isFeatured = true,
            isTrendingRecipe = true,
            chefNote = "Grate a small piece of beetroot into the steamed vegetables for that iconic ruby street-stall glow naturally.",
            chapters = listOf(
                VideoChapter("00:35", "Sizzling Butter & Aromatics", "Pair butter with a spoon of oil so milk solids never scorch."),
                VideoChapter("02:50", "Rhythmic Tawa Mashing", "Splash warm water while mashing to build glossy sheen."),
                VideoChapter("04:40", "Toasting Masala Ladi Pav", "Swipe sliced buns straight across the buttered masala rim.")
            ),
            ingredients = listOf(
                Ingredient("Potatoes, cauliflower & peas", 550.0, "g", "steamed fork-tender"),
                Ingredient("Ripe tomatoes & capsicum", 300.0, "g", "finely diced"),
                Ingredient("Pav bhaji masala", 2.5, "tbsp", "aromatic spice blend"),
                Ingredient("Kashmiri red chili paste", 1.5, "tbsp", "soaked & ground"),
                Ingredient("Salted butter", 70.0, "g", "for bhaji & pav"),
                Ingredient("Soft ladi pav buns", 8.0, "pcs", "halved for toasting")
            ),
            steps = listOf(
                CookingStep(1, "Sizzle Aromatics", "Melt butter on a wide griddle, sauté onions, bell pepper, and ginger-garlic paste until glossy.", 6, "00:35"),
                CookingStep(2, "Mash on the Tawa", "Add tomatoes, pav bhaji masala, chili paste, and steamed vegetables. Mash while splashing warm water until velvety.", 10, "02:50"),
                CookingStep(3, "Toast Masala Pav", "Swirl butter and a pinch of bhaji masala on the griddle, press buns crisp, and serve with raw onion and lime.", 4, "04:40")
            )
        ),
        CookingVideoItem(
            id = "vid_kesar_rasmalai",
            title = "Spongy Kesar Pista Rasmalai Without Cracks",
            recipeName = "Kesar Pista Rasmalai",
            subtitle = "Cloud-soft cottage cheese discs steeped in chilled Kashmiri saffron-cardamom milk and pistachios",
            region = "Kolkata",
            category = FoodCategory.SWEETS_BAKING,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN, DietaryPreference.GLUTEN_FREE),
            difficulty = DifficultyLevel.CHEF,
            durationLabel = "10:12",
            viewsCount = 205400,
            viewsLabel = "205K views",
            likesCount = 19100,
            publishedLabel = "1 week ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 30,
            cookTimeMinutes = 40,
            baseServings = 6,
            caloriesPerServing = 290,
            rating = 5.0,
            reviewCount = 367,
            creatorId = "creator_zoya",
            artworkStyle = DishArtworkStyle.KESAR_RASMALAI_BOWL,
            isFeatured = false,
            isTrendingRecipe = true,
            chefNote = "Knead fresh chhena with the heel of your palm for 7 minutes until butter-smooth for feather-light discs.",
            chapters = listOf(
                VideoChapter("01:05", "Curdling Tender Chhena", "Let milk cool 2 minutes after boiling before adding diluted lemon juice."),
                VideoChapter("03:45", "Rolling Boil Sugar Syrup", "Keep the lid closed so foamy steam puffs every disc evenly."),
                VideoChapter("06:30", "Steeping in Saffron Rabri", "Chill 4 hours so cardamom-saffron milk reaches the center.")
            ),
            ingredients = listOf(
                Ingredient("Whole milk", 2.0, "L", "1L for chhena, 1L for rabri"),
                Ingredient("Saffron strands", 0.5, "tsp", "steeped in warm milk"),
                Ingredient("Green cardamom pods", 6.0, "pcs", "freshly crushed"),
                Ingredient("Slivered pistachios & almonds", 45.0, "g", "garnish"),
                Ingredient("Cane sugar", 180.0, "g", "divided for syrup & rabri"),
                Ingredient("Fresh lemon juice", 2.0, "tbsp", "diluted with water")
            ),
            steps = listOf(
                CookingStep(1, "Curdle & Knead Chhena", "Curdle 1L milk gently, drain in cheesecloth, rinse lemon tang, and knead 7 minutes until silky.", 15, "01:05"),
                CookingStep(2, "Poach Spongy Discs", "Shape smooth discs and boil covered in light sugar syrup for 12 minutes until doubled.", 14, "03:45"),
                CookingStep(3, "Steep in Saffron Rabri", "Reduce second liter of milk with saffron, cardamom, and sugar. Squeeze poached discs and chill 4 hours.", 25, "06:30")
            )
        ),
        CookingVideoItem(
            id = "vid_palak_paneer",
            title = "25-Minute Burnt-Garlic Palak Paneer",
            recipeName = "Burnt-Garlic Palak Paneer",
            subtitle = "Vibrant emerald blanched spinach gravy topped with sizzling golden garlic tadka and soft paneer",
            region = "Northern India",
            category = FoodCategory.QUICK_WEEKNIGHT,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN, DietaryPreference.GLUTEN_FREE, DietaryPreference.HIGH_PROTEIN, DietaryPreference.NUT_FREE),
            difficulty = DifficultyLevel.EASY,
            durationLabel = "06:10",
            viewsCount = 118400,
            viewsLabel = "118K views",
            likesCount = 10400,
            publishedLabel = "1 week ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 10,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 310,
            rating = 4.8,
            reviewCount = 221,
            creatorId = "creator_rohan",
            artworkStyle = DishArtworkStyle.PALAK_PANEER_BOWL,
            isFeatured = false,
            isTrendingRecipe = true,
            chefNote = "Shock blanched spinach in ice water for 60 seconds to lock in bright jewel-green color.",
            chapters = listOf(
                VideoChapter("00:30", "Blanch & Ice-Shock Greens", "90 seconds in boiling salted water keeps spinach vivid green."),
                VideoChapter("02:20", "Quick Onion-Tomato Masala", "Keep spices light so sweet baby spinach shines."),
                VideoChapter("04:30", "Sizzling Burnt-Garlic Tadka", "Toast sliced garlic in ghee until just golden amber.")
            ),
            ingredients = listOf(
                Ingredient("Fresh baby spinach", 400.0, "g", "washed & stemmed"),
                Ingredient("Fresh paneer", 300.0, "g", "cut into cubes"),
                Ingredient("Garlic cloves", 10.0, "cloves", "thinly sliced for tadka"),
                Ingredient("Cumin seeds & garam masala", 1.5, "tsp", "warm spice"),
                Ingredient("Ghee", 2.0, "tbsp", "for tempering")
            ),
            steps = listOf(
                CookingStep(1, "Blanch & Puree", "Blanch spinach 90 seconds, shock in ice water, and blend smooth with green chili and ginger.", 6, "00:30"),
                CookingStep(2, "Simmer Masala", "Sauté cumin, minced onion, and tomato until jammy, then fold in spinach puree and paneer.", 10, "02:20"),
                CookingStep(3, "Garlic Tadka Finish", "Pour sizzling golden garlic and dried red chili ghee directly over the bowl.", 4, "04:30")
            )
        ),
        CookingVideoItem(
            id = "vid_malabar_curry",
            title = "Malabar Coconut Curry in Clay Chatti",
            recipeName = "Malabar Coconut Fish Curry",
            subtitle = "Silky Kerala coconut milk curry infused with tangy kudampuli, pearl shallots, and curry leaves",
            region = "Kozhikode",
            category = FoodCategory.SOUTH_COASTAL,
            dietaryTags = listOf(DietaryPreference.GLUTEN_FREE, DietaryPreference.DAIRY_FREE, DietaryPreference.HIGH_PROTEIN),
            difficulty = DifficultyLevel.EASY,
            durationLabel = "06:50",
            viewsCount = 79300,
            viewsLabel = "79K views",
            likesCount = 6900,
            publishedLabel = "2 weeks ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 15,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 410,
            rating = 4.8,
            reviewCount = 194,
            creatorId = "creator_karthik",
            artworkStyle = DishArtworkStyle.MALABAR_FISH_CURRY,
            isFeatured = false,
            isTrendingRecipe = false,
            chefNote = "Swirl the pot gently instead of stirring with a spoon once delicate fish fillets are added.",
            chapters = listOf(
                VideoChapter("00:40", "Shallots in Cold-Pressed Coconut Oil", "Thinly sliced pearl shallots build natural sweetness."),
                VideoChapter("02:35", "Balancing Tamarind Tang", "Steep Malabar kudampuli in warm water first."),
                VideoChapter("04:50", "First-Press Coconut Milk Swirl", "Remove from flame before pouring thick coconut milk.")
            ),
            ingredients = listOf(
                Ingredient("Firm white fish steaks", 600.0, "g", "or tofu for plant-based"),
                Ingredient("Thick first-press coconut milk", 250.0, "ml", "for finish"),
                Ingredient("Thin coconut milk", 200.0, "ml", "for simmering"),
                Ingredient("Pearl shallots", 120.0, "g", "thinly sliced"),
                Ingredient("Fresh curry leaves", 25.0, "leaves", "divided"),
                Ingredient("Cold-pressed coconut oil", 3.0, "tbsp", "authentic coastal aroma")
            ),
            steps = listOf(
                CookingStep(1, "Sauté Shallots", "Toast fenugreek seeds, sliced shallots, crushed ginger, and curry leaves in coconut oil.", 8, "00:40"),
                CookingStep(2, "Simmer Spice Base", "Add Kashmiri chili, turmeric, tamarind water, and thin coconut milk. Simmer 6 minutes.", 6, "02:35"),
                CookingStep(3, "Poach & Swirl", "Poach fish 8 minutes covered. Pour thick coconut milk off heat and finish with raw coconut oil.", 8, "04:50")
            )
        ),
        CookingVideoItem(
            id = "vid_pindi_chole",
            title = "Amritsari Pindi Chole: Tea-Infused Chickpeas",
            recipeName = "Amritsari Pindi Chole",
            subtitle = "Rich dark spiced chickpeas simmered with roasted pomegranate seeds and fiery ginger juliennes",
            region = "Amritsar",
            category = FoodCategory.COMFORT_CURRIES,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN, DietaryPreference.VEGAN, DietaryPreference.GLUTEN_FREE, DietaryPreference.DAIRY_FREE, DietaryPreference.HIGH_PROTEIN),
            difficulty = DifficultyLevel.MEDIUM,
            durationLabel = "07:18",
            viewsCount = 112600,
            viewsLabel = "112K views",
            likesCount = 9800,
            publishedLabel = "1 week ago",
            resolutionTag = "1080p HD",
            prepTimeMinutes = 15,
            cookTimeMinutes = 35,
            baseServings = 4,
            caloriesPerServing = 390,
            rating = 4.9,
            reviewCount = 289,
            creatorId = "creator_ananya",
            artworkStyle = DishArtworkStyle.CHOLE_BHATURE_FEAST,
            isFeatured = false,
            isTrendingRecipe = false,
            chefNote = "Simmering chickpeas with a muslin pouch of Assam black tea leaves gives authentic street-stall depth.",
            chapters = listOf(
                VideoChapter("00:55", "Spiced Black-Tea Potli", "Tie whole cardamom, cloves, and tea leaves tightly in muslin."),
                VideoChapter("03:00", "Roasting Anardana Spices", "Tangy dried pomegranate seeds replace sour powders cleanly."),
                VideoChapter("05:10", "Sizzling Ginger-Chili Tadka", "Pour hot tempering tableside right before serving.")
            ),
            ingredients = listOf(
                Ingredient("Kabuli chickpeas", 300.0, "g", "soaked overnight"),
                Ingredient("Black tea bag & dried amla", 2.0, "pcs", "for rich color"),
                Ingredient("Roasted anardana", 1.5, "tbsp", "ground pomegranate seeds"),
                Ingredient("Onions & ripe tomatoes", 350.0, "g", "finely minced"),
                Ingredient("Ginger juliennes & green chilies", 30.0, "g", "for tadka")
            ),
            steps = listOf(
                CookingStep(1, "Infuse Chickpeas", "Cook soaked chickpeas with whole spices and black tea pouch until creamy.", 20, "00:55"),
                CookingStep(2, "Build Masala", "Brown onions deeply, add tomatoes, anardana, coriander, and garam masala.", 10, "03:00"),
                CookingStep(3, "Temper & Serve", "Simmer chickpeas, lightly crush a ladleful for body, and finish with hot ginger-chili tadka.", 6, "05:10")
            )
        ),
        CookingVideoItem(
            id = "vid_dal_baati",
            title = "Rajasthani Dal Baati Churma Feast",
            recipeName = "Rajasthani Dal Baati Churma",
            subtitle = "Crisp ghee-dipped baked whole-wheat baatis paired with five-lentil dal and cardamom churma",
            region = "Jaipur",
            category = FoodCategory.GRILL_ROAST,
            dietaryTags = listOf(DietaryPreference.VEGETARIAN),
            difficulty = DifficultyLevel.MEDIUM,
            durationLabel = "08:55",
            viewsCount = 88400,
            viewsLabel = "88K views",
            likesCount = 7400,
            publishedLabel = "2 weeks ago",
            resolutionTag = "4K UHD",
            prepTimeMinutes = 25,
            cookTimeMinutes = 40,
            baseServings = 4,
            caloriesPerServing = 520,
            rating = 4.9,
            reviewCount = 178,
            creatorId = "creator_ananya",
            artworkStyle = DishArtworkStyle.DAL_BAATI_CHURMA,
            isFeatured = false,
            isTrendingRecipe = false,
            chefNote = "Add coarse semolina to whole-wheat atta so baatis bake crisp outside and soak up warm spiced ghee.",
            chapters = listOf(
                VideoChapter("00:50", "Coarse Wheat Dough & Cracks", "Keep dough firm so golden fissures form in the oven."),
                VideoChapter("03:40", "Panchmel Five-Lentil Dal", "Balance earthy lentils with sizzling hing-garlic ghee."),
                VideoChapter("06:20", "Cardamom Jaggery Churma", "Pulse warm baatis with jaggery and toasted nuts.")
            ),
            ingredients = listOf(
                Ingredient("Coarse whole-wheat flour", 300.0, "g", "with 3 tbsp semolina"),
                Ingredient("Panchmel five-lentil mix", 200.0, "g", "washed & soaked"),
                Ingredient("Pure desi ghee", 90.0, "g", "for dough & dipping"),
                Ingredient("Jaggery & green cardamom", 60.0, "g", "for sweet churma")
            ),
            steps = listOf(
                CookingStep(1, "Bake Crisp Baatis", "Knead firm dough with ghee and ajwain, shape rounds, and bake at 190°C until golden.", 25, "00:50"),
                CookingStep(2, "Simmer Panchmel Dal", "Cook five lentils until creamy and top with sizzling hing-garlic Kashmiri chili tadka.", 15, "03:40"),
                CookingStep(3, "Crush Churma & Serve", "Pulse two warm baatis with jaggery and cardamom. Dip remaining baatis in warm ghee.", 8, "06:20")
            )
        )
    )

    val creatorDrafts: List<CreatorDraftPreview> = listOf(
        CreatorDraftPreview(
            id = "draft_1",
            title = "Kashmiri Saffron Kahwa & Almond Biscotti",
            creatorId = "creator_ananya",
            category = FoodCategory.SWEETS_BAKING,
            durationEstimate = "05:30",
            stageLabel = "Storyboard Ready",
            lastUpdated = "Updated 2h ago",
            artworkStyle = DishArtworkStyle.KESAR_RASMALAI_BOWL
        ),
        CreatorDraftPreview(
            id = "draft_2",
            title = "15-Minute Garlic Chili Hakka Noodles",
            creatorId = "creator_rohan",
            category = FoodCategory.QUICK_WEEKNIGHT,
            durationEstimate = "04:45",
            stageLabel = "Editing Chapters",
            lastUpdated = "Updated yesterday",
            artworkStyle = DishArtworkStyle.MUMBAI_PAV_BHAJI
        ),
        CreatorDraftPreview(
            id = "draft_3",
            title = "Filter Kaapi Panna Cotta with Palm Jaggery",
            creatorId = "creator_karthik",
            category = FoodCategory.SWEETS_BAKING,
            durationEstimate = "06:15",
            stageLabel = "Awaiting Review",
            lastUpdated = "Updated 2d ago",
            artworkStyle = DishArtworkStyle.MASALA_DOSA_THALI
        ),
        CreatorDraftPreview(
            id = "draft_4",
            title = "Lucknowi Jackfruit Galouti Kebabs",
            creatorId = "creator_zoya",
            category = FoodCategory.GRILL_ROAST,
            durationEstimate = "08:10",
            stageLabel = "Thumbnail Polish",
            lastUpdated = "Updated 3d ago",
            artworkStyle = DishArtworkStyle.PANEER_TIKKA_PLATTER
        )
    )

    val moderationItems: List<AdminModerationItem> = listOf(
        AdminModerationItem(
            id = "mod_101",
            title = "Konkan Kokum Solkadhi & Crispy Rava Fry",
            creatorId = "creator_rohan",
            creatorName = "Rohan Deshmukh",
            creatorHandle = "@mumbaistreetcraft",
            category = FoodCategory.SOUTH_COASTAL,
            durationLabel = "06:45",
            submittedAt = "Today, 09:40 AM",
            status = ModerationStatus.PENDING,
            summaryNote = "Includes 4 chapter timestamps, full gluten-free & dairy-free ingredient checklist.",
            artworkStyle = DishArtworkStyle.MALABAR_FISH_CURRY
        ),
        AdminModerationItem(
            id = "mod_102",
            title = "Lucknowi Plant-Based Jackfruit Galouti Kebab",
            creatorId = "creator_zoya",
            creatorName = "Zoya Qureshi",
            creatorHandle = "@dumbyzoya",
            category = FoodCategory.GRILL_ROAST,
            durationLabel = "08:15",
            submittedAt = "Yesterday, 05:15 PM",
            status = ModerationStatus.PENDING,
            summaryNote = "Vegan & dairy-free tags verified; waiting for final cover frame sign-off.",
            artworkStyle = DishArtworkStyle.PANEER_TIKKA_PLATTER
        ),
        AdminModerationItem(
            id = "mod_103",
            title = "Filter Kaapi Panna Cotta with Jaggery Caramel",
            creatorId = "creator_karthik",
            creatorName = "Karthik Iyer",
            creatorHandle = "@tiffinwithkarthik",
            category = FoodCategory.SWEETS_BAKING,
            durationLabel = "04:50",
            submittedAt = "2 days ago",
            status = ModerationStatus.APPROVED,
            summaryNote = "Clean ingredient ratios, crisp 4K chapters, ready for featured dessert collection.",
            artworkStyle = DishArtworkStyle.KESAR_RASMALAI_BOWL
        ),
        AdminModerationItem(
            id = "mod_104",
            title = "Smoked Charcoal Dal Makhani (Overnight Simmer)",
            creatorId = "creator_ananya",
            creatorName = "Chef Ananya Verma",
            creatorHandle = "@ananyaskitchen",
            category = FoodCategory.COMFORT_CURRIES,
            durationLabel = "11:05",
            submittedAt = "3 days ago",
            status = ModerationStatus.NEEDS_EDITS,
            summaryNote = "Requested clearer serving scale measurements for whole black urad lentils.",
            artworkStyle = DishArtworkStyle.BUTTER_CHICKEN_HANDI
        )
    )
}
