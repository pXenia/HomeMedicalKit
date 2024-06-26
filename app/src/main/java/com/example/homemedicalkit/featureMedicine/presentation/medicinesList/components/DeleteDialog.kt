package com.example.homemedicalkit.presentation.medicinesList.components

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
import com.example.homemedicalkit.presentation.medicinesList.MedicineEvent
import com.example.homemedicalkit.presentation.medicinesList.MedicinesViewModel
import com.example.homemedicalkit.ui.theme.BlueSerface
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlueOutlined


@Composable
fun DeleteDialog(
    medicineId: Int,
    navController:  NavController,
    viewModel: MedicinesViewModel = hiltViewModel(),
    ) {
    AlertDialog(
        containerColor = BlueSerface,
            onDismissRequest = { },
            title = {},
            text = {
                Text(
                    "Удалить препарат?",
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
                        viewModel.onEvent(MedicineEvent.DeleteMedicine(medicineId))
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
            },
    )

}
