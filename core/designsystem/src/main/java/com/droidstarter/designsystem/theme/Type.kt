package com.droidstarter.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.droidstarter.designsystem.R

private val fontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.poppins_regular, weight = FontWeight.Normal, style = FontStyle.Normal
        ),
        Font(
            resId = R.font.poppins_medium, weight = FontWeight.SemiBold, style = FontStyle.Normal
        ),
        Font(
            resId = R.font.poppins_bold, weight = FontWeight.Bold, style = FontStyle.Normal
        ),
        Font(
            resId = R.font.poppins_italic, weight = FontWeight.Normal, style = FontStyle.Italic
        ),
    )
)


class AppTypography(dimen: AppDimen) {
    private val defaultTypography = Typography()

    private val displayLarge = dimen.displayLarge
    private val headlineLarge = dimen.headlineLarge
    private val titleLarge = dimen.titleLarge
    private val bodyLarge = dimen.bodyLarge
    private val labelLarge = dimen.labelLarge

    private val mediumFontSizeScaleFactor = 1.25
    private val smallFontSizeScaleFactor = mediumFontSizeScaleFactor * 2

    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(
            fontFamily = fontFamily, fontSize = displayLarge
        ),
        displayMedium = defaultTypography.displayMedium.copy(
            fontFamily = fontFamily, fontSize = displayLarge / mediumFontSizeScaleFactor
        ),
        displaySmall = defaultTypography.displaySmall.copy(
            fontFamily = fontFamily, fontSize = displayLarge / smallFontSizeScaleFactor
        ),
        headlineLarge = defaultTypography.headlineLarge.copy(
            fontFamily = fontFamily,
            fontSize = headlineLarge,
            fontWeight = FontWeight.Bold,
        ),
        headlineMedium = defaultTypography.headlineMedium.copy(
            fontFamily = fontFamily,
            fontSize = headlineLarge / mediumFontSizeScaleFactor,
            fontWeight = FontWeight.Bold,
        ),
        headlineSmall = defaultTypography.headlineSmall.copy(
            fontFamily = fontFamily, fontSize = headlineLarge / smallFontSizeScaleFactor
        ),
        titleLarge = defaultTypography.titleLarge.copy(
            fontFamily = fontFamily,
            fontSize = titleLarge,
            fontWeight = FontWeight.SemiBold,
        ),
        titleMedium = defaultTypography.titleMedium.copy(
            fontFamily = fontFamily,
            fontSize = titleLarge / mediumFontSizeScaleFactor,
        ),
        titleSmall = defaultTypography.titleSmall.copy(
            fontFamily = fontFamily, fontSize = titleLarge / smallFontSizeScaleFactor
        ),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = fontFamily, fontSize = bodyLarge),
        bodyMedium = defaultTypography.bodyMedium.copy(
            fontFamily = fontFamily, fontSize = bodyLarge / mediumFontSizeScaleFactor, fontWeight = FontWeight.Normal
        ),
        bodySmall = defaultTypography.bodySmall.copy(
            fontFamily = fontFamily,
            fontSize = bodyLarge / smallFontSizeScaleFactor,
            fontWeight = FontWeight.Normal,
        ),
        labelLarge = defaultTypography.labelLarge.copy(
            fontFamily = fontFamily,
            fontSize = labelLarge,
            fontWeight = FontWeight.Normal,
        ),
        labelMedium = defaultTypography.labelMedium.copy(
            fontFamily = fontFamily,
            fontSize = labelLarge / mediumFontSizeScaleFactor,
        ),
        labelSmall = defaultTypography.labelSmall.copy(
            fontFamily = fontFamily,
            fontSize = labelLarge / smallFontSizeScaleFactor
        ),
    )
}