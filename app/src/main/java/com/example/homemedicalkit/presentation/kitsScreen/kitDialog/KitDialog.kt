package com.example.homemedicalkit.presentation.kitsScreen.kitDialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homemedicalkit.ui.theme.BlueSerface
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlueOutlined
import com.example.homemedicalkit.ui.theme.Green80
import com.example.homemedicalkit.ui.theme.LightPurple80
import com.example.homemedicalkit.ui.theme.Orange80
import com.example.homemedicalkit.ui.theme.Red80
import com.example.homemedicalkit.ui.theme.Turquoise80
import com.example.homemedicalkit.ui.theme.Yellow80

@Composable
fun KitDialog(
    navController: NavController,
    viewModel: AddEditKitViewModel = hiltViewModel(),
) {
    AlertDialog(
        containerColor = BlueSerface,
        onDismissRequest = { navController.navigateUp() },
        title = {
            Text(
                text = "Новая аптечка",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = Comfortaa,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = DarkBlueOutlined
                )
            )
        },
        text = {
            Column {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(7) {
                        ColorPicker(colorId = it, viewModel = viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(15.dp),
                    value = viewModel.kitName.value,
                    onValueChange = {
                        viewModel.onEvent(AddEditKitEvent.EnteredName(it))
                    },
                    placeholder = {
                        Text(
                            "Название",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontFamily = Comfortaa,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp,
                                color = DarkBlueOutlined
                            )
                        )
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                {
                    viewModel.onEvent(AddEditKitEvent.SaveKit)
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = "ОК",
                    style = TextStyle(
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkBlueOutlined
                    )
                )
            }
        },
        dismissButton = {
            TextButton(
                {
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = "Отмена",
                    style = TextStyle(
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkBlueOutlined
                    )
                )
            }
        }
    )
}



@Composable
fun ColorPicker(colorId: Int, viewModel: AddEditKitViewModel){
    val colors = listOf(Red80, Yellow80, Green80, Orange80, Yellow80, LightPurple80, Turquoise80)
OutlinedCard(
    modifier = Modifier
        .size(32.dp)
        .clickable {
            viewModel.onEvent(AddEditKitEvent.ChangeColor(colorId))
        },
    shape = CircleShape,
    border = BorderStroke(2.dp, (if (viewModel.kitColor.value == colorId) {
        DarkBlueOutlined
    } else Color.Transparent)),
    colors = CardDefaults.cardColors(colors[colorId])
) {}
}