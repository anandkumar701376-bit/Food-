package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val CookHubLightColorScheme = lightColorScheme(
    primary = SunsetOrange,
    onPrimary = PureIvory,
    primaryContainer = ApricotSoft,
    onPrimaryContainer = BurntOrangeDeep,
    secondary = EspressoMedium,
    onSecondary = PureIvory,
    secondaryContainer = SaffronSoft,
    onSecondaryContainer = SaffronDark,
    tertiary = HerbGreen,
    onTertiary = PureIvory,
    tertiaryContainer = HerbSoft,
    onTertiaryContainer = Color(0xFF144527),
    background = CreamCanvas,
    onBackground = EspressoDark,
    surface = CreamCanvas,
    onSurface = EspressoDark,
    surfaceVariant = WarmAlmond,
    onSurfaceVariant = CinnamonMuted,
    surfaceContainerLowest = PureIvory,
    surfaceContainerLow = WarmAlmond,
    surfaceContainer = WarmSand,
    surfaceContainerHigh = Color(0xFFEFDEC8),
    outline = WarmOutline,
    outlineVariant = WarmOutlineSubtle,
    error = ChiliCrimson,
    onError = PureIvory,
    errorContainer = ChiliSoft,
    onErrorContainer = Color(0xFF7F1D1D)
)

private val CookHubDarkColorScheme = darkColorScheme(
    primary = OrangeGlowLight,
    onPrimary = Color(0xFF3B1304),
    primaryContainer = BurntOrangeDeep,
    onPrimaryContainer = ApricotSoft,
    secondary = SaffronGold,
    onSecondary = Color(0xFF2B1802),
    secondaryContainer = Color(0xFF4A2E0B),
    onSecondaryContainer = SaffronSoft,
    tertiary = HerbLight,
    onTertiary = Color(0xFF0D2818),
    tertiaryContainer = Color(0xFF1E442C),
    onTertiaryContainer = HerbSoft,
    background = DarkEspressoCanvas,
    onBackground = DarkCreamInk,
    surface = DarkEspressoCanvas,
    onSurface = DarkCreamInk,
    surfaceVariant = DarkRoastSurface,
    onSurfaceVariant = DarkMutedInk,
    surfaceContainerLowest = Color(0xFF140D09),
    surfaceContainerLow = DarkRoastSurface,
    surfaceContainer = DarkRoastElevated,
    surfaceContainerHigh = Color(0xFF3D2C22),
    outline = DarkWarmOutline,
    outlineVariant = Color(0xFF35261D),
    error = Color(0xFFFCA5A5),
    onError = Color(0xFF7F1D1D),
    errorContainer = Color(0xFF991B1B),
    onErrorContainer = ChiliSoft
)

val CookHubShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun CookHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) CookHubDarkColorScheme else CookHubLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CookHubTypography,
        shapes = CookHubShapes,
        content = content
    )
}
