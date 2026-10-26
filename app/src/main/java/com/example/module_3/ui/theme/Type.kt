package com.example.module_3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.module_3.R

val RussoOne = FontFamily(
    Font(R.font.russo_one_regular, FontWeight.Normal)
)

// Стандартные стили Material 3, но со шрифтом Russo One
private val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = RussoOne),
    displayMedium = baseline.displayMedium.copy(fontFamily = RussoOne),
    displaySmall = baseline.displaySmall.copy(fontFamily = RussoOne),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = RussoOne),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = RussoOne),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = RussoOne),
    titleLarge = baseline.titleLarge.copy(fontFamily = RussoOne),
    titleMedium = baseline.titleMedium.copy(fontFamily = RussoOne),
    titleSmall = baseline.titleSmall.copy(fontFamily = RussoOne),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = RussoOne),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = RussoOne),
    bodySmall = baseline.bodySmall.copy(fontFamily = RussoOne),
    labelLarge = baseline.labelLarge.copy(fontFamily = RussoOne),
    labelMedium = baseline.labelMedium.copy(fontFamily = RussoOne),
    labelSmall = baseline.labelSmall.copy(fontFamily = RussoOne)
)
