package com.example.homemedicalkit.presentation.medicinesList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homemedicalkit.R
import com.example.homemedicalkit.feature_medicine.domain.util.MedicineOrder
import com.example.homemedicalkit.feature_medicine.domain.util.OrderType
import com.example.homemedicalkit.ui.theme.DarkLavender200


@Composable
fun SortParam(medicineOrder: MedicineOrder,
              onOrderChange: (MedicineOrder) -> Unit) {
    Row(modifier =  Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
        Row(
            modifier = Modifier.clickable {
                val newOrder = if (medicineOrder !is MedicineOrder.Date) {
                    MedicineOrder.Date(OrderType.Descending)
                } else if (medicineOrder.orderType == OrderType.Descending) {
                    MedicineOrder.Date(OrderType.Ascending)
                } else {
                    MedicineOrder.Date(OrderType.Descending)
                }
                onOrderChange(newOrder)
            }
        ) {
            TitleParam(
                title = R.string.date,
                isSelected = medicineOrder is MedicineOrder.Date,
                isDescending = medicineOrder.orderType == OrderType.Descending
            )
        }
        Row(
            modifier = Modifier.clickable {
                val newOrder = if (medicineOrder !is MedicineOrder.Name) {
                    MedicineOrder.Name(OrderType.Descending)
                } else if (medicineOrder.orderType == OrderType.Descending) {
                    MedicineOrder.Name(OrderType.Ascending)
                } else {
                    MedicineOrder.Name(OrderType.Descending)
                }
                onOrderChange(newOrder)
            }
        ) {
            TitleParam(
                title = R.string.title,
                isSelected = medicineOrder is MedicineOrder.Name,
                isDescending = medicineOrder.orderType == OrderType.Descending
            )
        }
        Row(
            modifier = Modifier.clickable {
                val newOrder = if (medicineOrder !is MedicineOrder.Few) {
                    MedicineOrder.Few(OrderType.Descending)
                } else if (medicineOrder.orderType == OrderType.Descending) {
                    MedicineOrder.Few(OrderType.Ascending)
                } else {
                    MedicineOrder.Few(OrderType.Descending)
                }
                onOrderChange(newOrder)
            }
        ) {
            TitleParam(
                title = R.string.amount,
                isSelected = medicineOrder is MedicineOrder.Few,
                isDescending = medicineOrder.orderType == OrderType.Descending
            )
        }
    }
}

@Composable
fun TitleParam(title: Int, isSelected: Boolean, isDescending: Boolean) {
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.labelSmall.copy(
            color = if (isSelected) DarkLavender200 else Color.Gray
        )
    )
    Icon(
        modifier = Modifier.height(20.dp),
        imageVector = if (isDescending) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
        contentDescription = "Sort Order",
        tint = if (isSelected) DarkLavender200 else Color.Gray
    )
}
