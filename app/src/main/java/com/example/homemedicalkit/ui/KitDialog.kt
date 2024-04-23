package com.example.homemedicalkit.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkLavender200
import com.example.homemedicalkit.ui.theme.LavenderD1D5F0
import com.example.homemedicalkit.ui.theme.Red80

@Preview
@Composable
fun KitDialog() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            containerColor = LavenderD1D5F0,
            onDismissRequest = { openDialog.value = false},
            title = { Text(
                text = "Новая аптечка",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = Comfortaa,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = DarkLavender200
                )
            ) },
            text = {
                   LazyRow(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.spacedBy(5.dp)){
                       items(10){ColorPicker()}
                   }
            },
            confirmButton = {
                TextButton({ openDialog.value = false }) {
                    Text(
                        text = "ОК",
                        style = TextStyle(
                            fontFamily = Comfortaa,
                            fontWeight = FontWeight.ExtraBold,
                            color = DarkLavender200
                        )
                    )
                }
            }
        )
    }
}
@Preview
@Composable
fun ColorPicker(){
OutlinedCard(
    modifier = Modifier.size(32.dp),
    shape = CircleShape,
    border = BorderStroke(2.dp, DarkLavender200),
    colors = CardDefaults.cardColors(Red80)
) {}
}