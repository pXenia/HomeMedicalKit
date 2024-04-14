package com.example.homemedicalkit.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homemedicalkit.ViewModel.AddEditMedicineEvent
import com.example.homemedicalkit.ViewModel.AddEditMedicineViewModel
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.LavenderD1D5F0
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateAlertDialog(viewModel: AddEditMedicineViewModel) {
    val state = rememberDatePickerState()
    val dateState = viewModel.medicineDate.value
    val openDialog = remember { mutableStateOf(false) }
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    OutlinedTextField(
        modifier = Modifier
            .height(48.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(LavenderD1D5F0),
        shape = RoundedCornerShape(30.dp),
        value = if (dateState != 0L) sdf.format(dateState) else "",
        readOnly = true,
        maxLines = 1,
        onValueChange = {},
        placeholder = {
            Text("01.01.2024",
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
            IconButton(onClick = { openDialog.value = true }) {
                Icon(Icons.Filled.CalendarMonth, "", tint = Color.Gray)
            }
        }
    )
    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        viewModel.onEvent(AddEditMedicineEvent.EnteredDate(state.selectedDateMillis ?: Calendar.getInstance().timeInMillis))
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }
}