package com.droidstarter.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat


@Composable
fun DroidTheme(isDarkTheme: Boolean = true, content: @Composable () -> Unit) {
    val sizeCategory = getSizeCategory(LocalWindowSize.current)

    val dimens = when (sizeCategory) {
        SizeClass.Compact -> CompactDimen
        SizeClass.Medium -> MediumDimens
        SizeClass.Big -> BigDimens
    }

    val typography = AppTypography(dimens)

    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalAppColors provides AppColors(colorScheme),
        LocalAppDimens provides dimens,
        LocalAppTypography provides typography,
        LocalAppShapes provides DefaultShapes
    ) {
        MaterialTheme(
            colorScheme = LocalAppColors.current.colorScheme,
            typography = LocalAppTypography.current.typography,
            shapes = LocalAppShapes.current.shapes,
            content = content,
        )
    }

}

private enum class SizeClass {
    Compact, Medium, Big
}

private fun getSizeCategory(windowSizeClass: WindowSizeClass): SizeClass {
    val widthClass = windowSizeClass.widthSizeClass
    val heightClass = windowSizeClass.heightSizeClass

    return when (widthClass) {
        WindowWidthSizeClass.Compact -> SizeClass.Compact

        WindowWidthSizeClass.Medium -> {
            when (heightClass) {
                WindowHeightSizeClass.Compact -> SizeClass.Compact
                WindowHeightSizeClass.Medium -> SizeClass.Medium
                WindowHeightSizeClass.Expanded -> SizeClass.Big
                else -> SizeClass.Medium
            }
        }

        WindowWidthSizeClass.Expanded -> {
            when (heightClass) {
                WindowHeightSizeClass.Compact -> SizeClass.Compact
                WindowHeightSizeClass.Medium -> SizeClass.Medium
                WindowHeightSizeClass.Expanded -> SizeClass.Big
                else -> SizeClass.Big
            }
        }

        else -> SizeClass.Big
    }
}

/**
 * Used to calculate window size and use corresponding dimens
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun DroidActivityTheme(
    activity: Activity, isDarkTheme: Boolean = true, content: @Composable () -> Unit
) {
    val windowSize = calculateWindowSizeClass(activity = activity)

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        CompositionLocalProvider(
            LocalWindowSize provides windowSize
        ) {
            DroidTheme(isDarkTheme = isDarkTheme) {
                val bgColor = MaterialTheme.colorScheme.background.toArgb()
                val view = LocalView.current
                SideEffect {
                    activity.window.statusBarColor = bgColor
                    WindowCompat.getInsetsController(activity.window, view).apply {
                        isAppearanceLightStatusBars = !isDarkTheme
                        isAppearanceLightNavigationBars = !isDarkTheme
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background, content = content
                )
            }
        }
    }
}

val LocalAppColors = compositionLocalOf { AppColors(DarkColorScheme) }
val LocalAppDimens = compositionLocalOf<AppDimen> { BigDimens }
val LocalAppTypography = compositionLocalOf { AppTypography(BigDimens) }
val LocalAppShapes = compositionLocalOf { DefaultShapes }

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val LocalWindowSize = compositionLocalOf<WindowSizeClass> { WindowSizeClass.calculateFromSize(DpSize.Zero) }

val MaterialTheme.appDimens: AppDimen
    @Composable @ReadOnlyComposable get() = LocalAppDimens.current

val MaterialTheme.appShapes: AppShape
    @Composable @ReadOnlyComposable get() = LocalAppShapes.current

val MaterialTheme.windowSize: WindowSizeClass
    @Composable @ReadOnlyComposable get() = LocalWindowSize.current

val MaterialTheme.appColors: AppColors @Composable @ReadOnlyComposable get() = LocalAppColors.current

@Composable
fun CardDefaults.elevatedCard(): CardElevation {
    return this.elevatedCardElevation(defaultElevation = 6.dp, pressedElevation = 2.dp)
}

@Composable
fun CardDefaults.smallElevatedCard(): CardElevation {
    return this.elevatedCardElevation(defaultElevation = MaterialTheme.appDimens.smallElevation)
}