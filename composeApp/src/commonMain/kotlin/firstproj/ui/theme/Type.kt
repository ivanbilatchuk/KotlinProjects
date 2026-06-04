package firstproj.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

private val baseline = Typography()

fun getTypography(customFont: Font): Typography {
    val audiowideFamily = FontFamily(customFont)
    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = audiowideFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = audiowideFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = audiowideFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = audiowideFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = audiowideFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = audiowideFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = audiowideFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = audiowideFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = audiowideFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = audiowideFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = audiowideFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = audiowideFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = audiowideFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = audiowideFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = audiowideFamily),
    )
}
