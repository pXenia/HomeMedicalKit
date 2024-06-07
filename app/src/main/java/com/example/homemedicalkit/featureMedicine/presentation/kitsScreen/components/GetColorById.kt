package com.example.homemedicalkit.featureMedicine.presentation.kitsScreen.components

import androidx.compose.ui.graphics.Color
import com.example.homemedicalkit.ui.theme.Green80
import com.example.homemedicalkit.ui.theme.LightPurple80
import com.example.homemedicalkit.ui.theme.Orange80
import com.example.homemedicalkit.ui.theme.Purple80
import com.example.homemedicalkit.ui.theme.Red80
import com.example.homemedicalkit.ui.theme.Turquoise80
import com.example.homemedicalkit.ui.theme.Yellow80

fun getColorById(colorId: Int): Color {
    val colors = listOf(Red80, Yellow80, Green80, Orange80, Purple80 , LightPurple80, Turquoise80)
    return colors.getOrElse(colorId) { Color.Transparent }
}