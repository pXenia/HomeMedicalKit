package com.example.homemedicalkit.presentation.medicineCard.components

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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homemedicalkit.R
import com.example.homemedicalkit.presentation.medicineCard.AddEditMedicineEvent
import com.example.homemedicalkit.presentation.medicineCard.AddEditMedicineViewModel
import com.example.homemedicalkit.ui.theme.Blue1
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
            .height(dimensionResource(R.dimen.out_lined_text_field_height))
            .width(150.dp)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.rounded_corner)))
            .background(Blue1),
        shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_corner)),
        value = if (dateState != 0L) sdf.format(dateState) else "",
        readOnly = true,
        singleLine = true,
        onValueChange = {},
        placeholder = {
            Text(
                stringResource(R.string._01_01_2024),
                style = MaterialTheme.typography.bodySmall
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
                    Text(stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
        ) {
            DatePicker(
                state = state
            )
        }
    }
}