package com.example.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.screens.AdminDashboardScreen
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.CreatorDashboardScreen
import com.example.ui.screens.CreatorProfileScreen
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.VideoDetailScreen
import com.example.ui.state.CookHubViewModel

object CookHubRoutes {
    const val HOME = "home"
    const val EXPLORE = "explore"
    const val CATEGORIES = "categories"
    const val CREATOR_DASHBOARD = "creator_dashboard"
    const val ADMIN_DASHBOARD = "admin_dashboard"
    const val AUTH = "auth"
    const val VIDEO_DETAIL = "video/{videoId}"
    const val CREATOR_PROFILE = "creator/{creatorId}"

    fun videoDetail(videoId: String) = "video/$videoId"
    fun creatorProfile(creatorId: String) = "creator/$creatorId"
}

private data class TopLevelDestination(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val TopLevelDestinations = listOf(
    TopLevelDestination(CookHubRoutes.HOME, "Home", Icons.Filled.Home, Icons.Outlined.Home),
    TopLevelDestination(CookHubRoutes.EXPLORE, "Explore", Icons.Filled.Explore, Icons.Outlined.Explore),
    TopLevelDestination(CookHubRoutes.CATEGORIES, "Categories", Icons.Filled.GridView, Icons.Outlined.GridView),
    TopLevelDestination(CookHubRoutes.CREATOR_DASHBOARD, "Creator", Icons.Filled.Dashboard, Icons.Outlined.Dashboard),
    TopLevelDestination(CookHubRoutes.ADMIN_DASHBOARD, "Admin", Icons.Filled.AdminPanelSettings, Icons.Outlined.AdminPanelSettings)
)

@Composable
fun CookHubApp(
    viewModel: CookHubViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val topLevelRoutes = remember { TopLevelDestinations.map { it.route }.toSet() }
    val showTopLevelNav = currentDestination?.route in topLevelRoutes

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearMessage()
        }
    }

    fun navigateTopLevel(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useNavigationRail = maxWidth >= 600.dp

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                if (showTopLevelNav && !useNavigationRail) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        windowInsets = WindowInsets.navigationBars
                    ) {
                        TopLevelDestinations.forEach { dest ->
                            val selected = currentDestination?.hierarchy?.any { it.route == dest.route } == true
                            NavigationBarItem(
                                selected = selected,
                                onClick = { navigateTopLevel(dest.route) },
                                modifier = Modifier.testTag("nav_${dest.route}"),
                                icon = {
                                    if (dest.route == CookHubRoutes.ADMIN_DASHBOARD && uiState.pendingReviewCount > 0) {
                                        BadgedBox(
                                            badge = {
                                                Badge(containerColor = MaterialTheme.colorScheme.primary) {
                                                    Text("${uiState.pendingReviewCount}")
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                                                contentDescription = dest.label
                                            )
                                        }
                                    } else {
                                        Icon(
                                            imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                                            contentDescription = dest.label
                                        )
                                    }
                                },
                                label = {
                                    Text(
                                        text = dest.label,
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (showTopLevelNav && useNavigationRail) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                    ) {
                        TopLevelDestinations.forEach { dest ->
                            val selected = currentDestination?.hierarchy?.any { it.route == dest.route } == true
                            NavigationRailItem(
                                selected = selected,
                                onClick = { navigateTopLevel(dest.route) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                                        contentDescription = dest.label
                                    )
                                },
                                label = { Text(dest.label) },
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = CookHubRoutes.HOME,
                        modifier = Modifier
                            .fillMaxSize()
                            .widthIn(max = 880.dp)
                    ) {
                        composable(CookHubRoutes.HOME) {
                            HomeScreen(
                                uiState = uiState,
                                onOpenExplore = { category ->
                                    if (category != null) viewModel.selectCategory(category)
                                    navigateTopLevel(CookHubRoutes.EXPLORE)
                                },
                                onOpenCategories = { navigateTopLevel(CookHubRoutes.CATEGORIES) },
                                onOpenVideoDetail = { videoId ->
                                    navController.navigate(CookHubRoutes.videoDetail(videoId))
                                },
                                onOpenCreatorProfile = { creatorId ->
                                    navController.navigate(CookHubRoutes.creatorProfile(creatorId))
                                },
                                onOpenCreatorDashboard = { navigateTopLevel(CookHubRoutes.CREATOR_DASHBOARD) },
                                onOpenAdminDashboard = { navigateTopLevel(CookHubRoutes.ADMIN_DASHBOARD) },
                                onOpenAuth = { navController.navigate(CookHubRoutes.AUTH) },
                                onToggleSaveVideo = viewModel::toggleSaveVideo
                            )
                        }

                        composable(CookHubRoutes.EXPLORE) {
                            ExploreScreen(
                                uiState = uiState,
                                onSearchQueryChange = viewModel::updateSearchQuery,
                                onSelectCategory = viewModel::selectCategory,
                                onSelectContentType = viewModel::selectContentType,
                                onToggleDietary = viewModel::toggleDietary,
                                onSelectDifficulty = viewModel::selectDifficulty,
                                onSelectSort = viewModel::selectSortOrder,
                                onClearFilters = viewModel::clearExploreFilters,
                                onOpenVideoDetail = { videoId ->
                                    navController.navigate(CookHubRoutes.videoDetail(videoId))
                                },
                                onOpenCreatorProfile = { creatorId ->
                                    navController.navigate(CookHubRoutes.creatorProfile(creatorId))
                                },
                                onToggleSaveVideo = viewModel::toggleSaveVideo
                            )
                        }

                        composable(CookHubRoutes.CATEGORIES) {
                            CategoriesScreen(
                                uiState = uiState,
                                onSelectSpotlightCategory = viewModel::selectSpotlightCategory,
                                onExploreCategory = { category ->
                                    viewModel.selectCategory(category)
                                    navigateTopLevel(CookHubRoutes.EXPLORE)
                                },
                                onOpenVideoDetail = { videoId ->
                                    navController.navigate(CookHubRoutes.videoDetail(videoId))
                                },
                                onToggleSaveVideo = viewModel::toggleSaveVideo
                            )
                        }

                        composable(
                            route = CookHubRoutes.VIDEO_DETAIL,
                            arguments = listOf(navArgument("videoId") { type = NavType.StringType })
                        ) { entry ->
                            val videoId = entry.arguments?.getString("videoId") ?: ""
                            val video = viewModel.findVideo(videoId) ?: uiState.videos.first()
                            val creator = viewModel.findCreator(video.creatorId)
                            val related = uiState.videos.filter { it.id != video.id }.take(5)
                            VideoDetailScreen(
                                video = video,
                                creator = creator,
                                relatedVideos = related,
                                allCreators = uiState.creators,
                                isSaved = video.id in uiState.savedVideoIds,
                                isLiked = video.id in uiState.likedVideoIds,
                                isFollowingCreator = video.creatorId in uiState.followedCreatorIds,
                                onBack = { navController.popBackStack() },
                                onToggleSave = { viewModel.toggleSaveVideo(video.id) },
                                onToggleLike = { viewModel.toggleLikeVideo(video.id) },
                                onToggleFollowCreator = { viewModel.toggleFollowCreator(video.creatorId) },
                                onOpenCreatorProfile = { creatorId ->
                                    navController.navigate(CookHubRoutes.creatorProfile(creatorId))
                                },
                                onOpenRelatedVideo = { nextId ->
                                    navController.navigate(CookHubRoutes.videoDetail(nextId))
                                }
                            )
                        }

                        composable(
                            route = CookHubRoutes.CREATOR_PROFILE,
                            arguments = listOf(navArgument("creatorId") { type = NavType.StringType })
                        ) { entry ->
                            val creatorId = entry.arguments?.getString("creatorId") ?: ""
                            val creator = viewModel.findCreator(creatorId) ?: uiState.creators.first()
                            val creatorVideos = uiState.videos.filter { it.creatorId == creator.id }
                            val otherCreators = uiState.creators.filter { it.id != creator.id }
                            CreatorProfileScreen(
                                creator = creator,
                                creatorVideos = creatorVideos,
                                otherCreators = otherCreators,
                                allVideos = uiState.videos,
                                savedVideoIds = uiState.savedVideoIds,
                                isFollowing = creator.id in uiState.followedCreatorIds,
                                onBack = { navController.popBackStack() },
                                onToggleFollow = { viewModel.toggleFollowCreator(creator.id) },
                                onOpenVideoDetail = { videoId ->
                                    navController.navigate(CookHubRoutes.videoDetail(videoId))
                                },
                                onToggleSaveVideo = viewModel::toggleSaveVideo,
                                onOpenOtherCreator = { nextCreatorId ->
                                    navController.navigate(CookHubRoutes.creatorProfile(nextCreatorId))
                                },
                                onOpenCreatorDashboard = {
                                    viewModel.selectStudioCreator(creator.id)
                                    navigateTopLevel(CookHubRoutes.CREATOR_DASHBOARD)
                                }
                            )
                        }

                        composable(CookHubRoutes.CREATOR_DASHBOARD) {
                            CreatorDashboardScreen(
                                uiState = uiState,
                                onSelectStudioCreator = viewModel::selectStudioCreator,
                                onOpenVideoDetail = { videoId ->
                                    navController.navigate(CookHubRoutes.videoDetail(videoId))
                                },
                                onOpenCreatorProfile = { creatorId ->
                                    navController.navigate(CookHubRoutes.creatorProfile(creatorId))
                                },
                                onToggleSaveVideo = viewModel::toggleSaveVideo
                            )
                        }

                        composable(CookHubRoutes.ADMIN_DASHBOARD) {
                            AdminDashboardScreen(
                                uiState = uiState,
                                onSelectStatusFilter = viewModel::selectAdminStatusFilter,
                                onOpenCreatorProfile = { creatorId ->
                                    navController.navigate(CookHubRoutes.creatorProfile(creatorId))
                                },
                                onOpenVideoDetail = { videoId ->
                                    navController.navigate(CookHubRoutes.videoDetail(videoId))
                                },
                                onToggleSaveVideo = viewModel::toggleSaveVideo
                            )
                        }

                        composable(CookHubRoutes.AUTH) {
                            AuthScreen(
                                onBack = { navController.popBackStack() },
                                onOpenHome = { navigateTopLevel(CookHubRoutes.HOME) },
                                onOpenCreatorDashboard = { navigateTopLevel(CookHubRoutes.CREATOR_DASHBOARD) },
                                onOpenAdminDashboard = { navigateTopLevel(CookHubRoutes.ADMIN_DASHBOARD) }
                            )
                        }
                    }
                }
            }
        }
    }
}
