package com.example.homemedicalkit.presentation.kitsScreen.kitDialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homemedicalkit.presentation.kitsScreen.KitsEvent
import com.example.homemedicalkit.presentation.kitsScreen.KitsViewModel
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.BlueSerface
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlueOutlined


@Composable
fun DeleteDialogKit(
    kitId: Int,
    navController:  NavController,
    viewModel: KitsViewModel = hiltViewModel(),
    ) {
    AlertDialog(
        containerColor = BlueSerface,
        onDismissRequest = {navController.navigateUp()},
        title = {},
        text = {
            Text(
                "Изменить аптечку?",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = Comfortaa,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = DarkBlueOutlined
                )
            )
        },
        confirmButton = {
            TextButton(
                {
                    viewModel.onEvent(KitsEvent.DeleteKit(kitId))
                    navController.navigateUp()
                }
            ) {
                Text(
                    text = "Удалить",
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
                    navController.navigate(
                        Screen.KitDialog.route + "?kitId=${kitId}")
                }
            ) {
                Text(
                    text = "Изменить",
                    style = TextStyle(
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkBlueOutlined
                    )
                )
            }
        },
    )

}
