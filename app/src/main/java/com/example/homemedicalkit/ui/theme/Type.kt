package com.example.homemedicalkit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.homemedicalkit.R

// Set of Material typography styles to start with
val Typography: Typography
    get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = Comfortaa,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.5.sp
        )
    )
val Comfortaa = FontFamily(
    Font(R.font.comfortaa_light, FontWeight.Normal),
    Font(R.font.comfortaa_bold, FontWeight.Bold)
)
