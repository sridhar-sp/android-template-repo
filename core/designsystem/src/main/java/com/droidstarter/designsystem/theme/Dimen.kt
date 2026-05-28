package com.droidstarter.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

interface AppDimen {

    val displayLarge: TextUnit

    val displayMedium: TextUnit
    val displaySmall: TextUnit
    val headlineLarge: TextUnit
    val headlineMedium: TextUnit
    val headlineSmall: TextUnit
    val titleLarge: TextUnit
    val titleMedium: TextUnit
    val titleSmall: TextUnit
    val bodyLarge: TextUnit
    val bodyMedium: TextUnit
    val bodySmall: TextUnit
    val labelLarge: TextUnit
    val labelMedium: TextUnit
    val labelSmall: TextUnit


    val microPadding: Dp
    val tinyPadding: Dp
    val smallPadding: Dp
    val mediumPadding: Dp
    val largePadding: Dp
    val extraLargePadding: Dp

    val smallElevation: Dp

    val themeIconSize: Dp
    val cardWidgetSize: Dp
    val typographyWidgetDemoHeight: Dp
}

class ScalableDimen(private val scaleFactor: Float = 1f) : AppDimen {

    override val displayLarge: TextUnit = 57.sp * scaleFactor
    override val displayMedium: TextUnit = 45.sp * scaleFactor
    override val displaySmall: TextUnit = 36.sp * scaleFactor
    override val headlineLarge: TextUnit = 32.sp * scaleFactor
    override val headlineMedium: TextUnit = 28.sp * scaleFactor
    override val headlineSmall: TextUnit = 24.sp * scaleFactor
    override val titleLarge: TextUnit = 22.sp * scaleFactor
    override val titleMedium: TextUnit = 16.sp * scaleFactor
    override val titleSmall: TextUnit = 14.sp * scaleFactor
    override val bodyLarge: TextUnit = 16.sp * scaleFactor
    override val bodyMedium: TextUnit = 14.sp * scaleFactor
    override val bodySmall: TextUnit = 12.sp * scaleFactor
    override val labelLarge: TextUnit = 14.sp * scaleFactor
    override val labelMedium: TextUnit = 12.sp * scaleFactor
    override val labelSmall: TextUnit = 11.sp * scaleFactor


    override val microPadding: Dp = 2.dp * scaleFactor
    override val tinyPadding: Dp = 4.dp * scaleFactor
    override val smallPadding: Dp = 8.dp * scaleFactor
    override val mediumPadding: Dp = 16.dp * scaleFactor
    override val largePadding: Dp = 24.dp * scaleFactor
    override val extraLargePadding: Dp = 32.dp * scaleFactor
    override val smallElevation: Dp = 2.dp * scaleFactor
    override val themeIconSize: Dp = 40.dp * scaleFactor

    override val cardWidgetSize: Dp = 126.dp * scaleFactor

    override val typographyWidgetDemoHeight: Dp = 250.dp * scaleFactor
}

val BigDimens = ScalableDimen(1.5f)
val MediumDimens = ScalableDimen(1.25f)
val CompactDimen = ScalableDimen(1f)