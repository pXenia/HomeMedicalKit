package com.example.homemedicalkit.presentation.medicinesList.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homemedicalkit.ui.theme.Comfortaa

@Composable
fun SearchBar() {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                "Найти ... ",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = Comfortaa,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
        },
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Search, "Search", tint = Color.Gray)
            }
        }
    )
}