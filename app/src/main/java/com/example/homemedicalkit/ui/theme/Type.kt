package com.example.homemedicalkit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        ),
        bodyMedium = TextStyle(
            textAlign = TextAlign.Center,
            fontFamily = Comfortaa,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            letterSpacing = 0.5.sp,
            color = DarkBlueOutlined,
            ),
        bodySmall = TextStyle(
            fontFamily = Comfortaa,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.5.sp,
            color = DarkBlueOutlined
        ),
        labelMedium = TextStyle(
            fontFamily = Comfortaa,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.5.sp,
            color = DarkBlueOutlined
        ),
        labelSmall = TextStyle(
            fontFamily = Comfortaa,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.5.sp,
            color = DarkBlueOutlined
        ),
        titleSmall = TextStyle(
            fontSize = 18.sp,
            fontFamily = Comfortaa,
            textAlign = TextAlign.Center,
        ),
        titleLarge = TextStyle(
            shadow = Shadow(DarkLavender200, blurRadius = 2f),
            fontFamily = Comfortaa,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            color = DarkLavender200
        )
    )
val Comfortaa = FontFamily(
    Font(R.font.comfortaa_light, FontWeight.Normal),
    Font(R.font.comfortaa_bold, FontWeight.Bold)
)
