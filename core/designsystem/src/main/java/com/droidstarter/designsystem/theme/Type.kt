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

    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(
            fontFamily = fontFamily,
            fontSize = dimen.displayLarge
        ),
        displayMedium = defaultTypography.displayMedium.copy(
            fontFamily = fontFamily,
            fontSize = dimen.displayMedium
        ),
        displaySmall = defaultTypography.displaySmall.copy(
            fontFamily = fontFamily,
            fontSize = dimen.displaySmall
        ),
        headlineLarge = defaultTypography.headlineLarge.copy(
            fontFamily = fontFamily,
            fontSize = dimen.headlineLarge,
        ),
        headlineMedium = defaultTypography.headlineMedium.copy(
            fontFamily = fontFamily,
            fontSize = dimen.headlineMedium,
        ),
        headlineSmall = defaultTypography.headlineSmall.copy(
            fontFamily = fontFamily,
            fontSize = dimen.headlineSmall
        ),
        titleLarge = defaultTypography.titleLarge.copy(
            fontFamily = fontFamily,
            fontSize = dimen.titleLarge,
        ),
        titleMedium = defaultTypography.titleMedium.copy(
            fontFamily = fontFamily,
            fontSize = dimen.titleMedium,
        ),
        titleSmall = defaultTypography.titleSmall.copy(
            fontFamily = fontFamily,
            fontSize = dimen.titleSmall
        ),
        bodyLarge = defaultTypography.bodyLarge.copy(
            fontFamily = fontFamily,
            fontSize = dimen.bodyLarge
        ),
        bodyMedium = defaultTypography.bodyMedium.copy(
            fontFamily = fontFamily,
            fontSize = dimen.bodyMedium,
        ),
        bodySmall = defaultTypography.bodySmall.copy(
            fontFamily = fontFamily,
            fontSize = dimen.bodySmall,
        ),
        labelLarge = defaultTypography.labelLarge.copy(
            fontFamily = fontFamily,
            fontSize = dimen.labelLarge,
        ),
        labelMedium = defaultTypography.labelMedium.copy(
            fontFamily = fontFamily,
            fontSize = dimen.labelMedium,
        ),
        labelSmall = defaultTypography.labelSmall.copy(
            fontFamily = fontFamily,
            fontSize = dimen.labelSmall
        ),
    )
}