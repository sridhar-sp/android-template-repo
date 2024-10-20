package com.droidstarter.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

interface AppDimen {

    val displayLarge: TextUnit
    val headlineLarge: TextUnit
    val titleLarge: TextUnit
    val bodyLarge: TextUnit
    val labelLarge: TextUnit

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

    override val displayLarge: TextUnit get() = 57.sp * scaleFactor
    override val headlineLarge: TextUnit get() = 32.sp * scaleFactor
    override val titleLarge: TextUnit get() = 22.sp * scaleFactor
    override val bodyLarge: TextUnit get() = 16.sp * scaleFactor
    override val labelLarge: TextUnit get() = 14.sp * scaleFactor
    override val microPadding: Dp get() = 2.dp * scaleFactor
    override val tinyPadding: Dp get() = 4.dp * scaleFactor
    override val smallPadding: Dp get() = 8.dp * scaleFactor
    override val mediumPadding: Dp get() = 16.dp * scaleFactor
    override val largePadding: Dp get() = 24.dp * scaleFactor
    override val extraLargePadding: Dp get() = 32.dp * scaleFactor
    override val smallElevation: Dp get() = 2.dp * scaleFactor
    override val themeIconSize: Dp get() = 40.dp * scaleFactor

    override val cardWidgetSize: Dp get() = 126.dp * scaleFactor

    override val typographyWidgetDemoHeight: Dp get() = 250.dp * scaleFactor
}

val BigDimens = ScalableDimen(1.5f)
val MediumDimens = ScalableDimen(1.25f)
val CompactDimen = ScalableDimen(1f)