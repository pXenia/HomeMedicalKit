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
    var _medicineOrder = medicineOrder
    Row(modifier =  Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
        Row(
            modifier = Modifier.clickable {
                _medicineOrder = if (_medicineOrder !is MedicineOrder.Date) {
                    MedicineOrder.Date(OrderType.Descending)
                }else if (_medicineOrder.orderType == OrderType.Descending){
                    MedicineOrder.Date(OrderType.Ascending)
                }else{
                    MedicineOrder.Date(OrderType.Descending)
                }
                onOrderChange(MedicineOrder.Date(_medicineOrder.orderType))}
        ) {
            TitleParam(title = R.string.date, medicineOrder = _medicineOrder)
        }
        Row(
            modifier = Modifier.clickable {
                _medicineOrder = if (_medicineOrder !is MedicineOrder.Name) {
                    MedicineOrder.Name(OrderType.Descending)
                }else if (_medicineOrder.orderType == OrderType.Descending){
                    MedicineOrder.Name(OrderType.Ascending)
                }else{
                    MedicineOrder.Name(OrderType.Descending)
                }
                onOrderChange(MedicineOrder.Name(_medicineOrder.orderType))
            }
        ) {
            TitleParam(title = R.string.title, medicineOrder = _medicineOrder)
        }
        Row(
            modifier = Modifier.clickable {
                _medicineOrder = if (_medicineOrder !is MedicineOrder.Few) {
                    MedicineOrder.Few(OrderType.Descending)
                }else if (_medicineOrder.orderType == OrderType.Descending){
                    MedicineOrder.Few(OrderType.Ascending)
                }else{
                    MedicineOrder.Few(OrderType.Descending)
                }
                onOrderChange(MedicineOrder.Few(_medicineOrder.orderType))}
        ) {
            TitleParam(title = R.string.amount, medicineOrder = _medicineOrder)
        }
    }
}

@Composable
fun TitleParam(title: Int, medicineOrder: MedicineOrder){
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.labelSmall.copy(
            color = if (medicineOrder is MedicineOrder.Date) DarkLavender200 else Color.Gray
        )
    )
    Icon(
        modifier = Modifier.height(20.dp),
        imageVector = if (medicineOrder.orderType is OrderType.Descending  &&  medicineOrder is MedicineOrder.Date)
            Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
        contentDescription = "Add",
        tint = if (medicineOrder is MedicineOrder.Date) DarkLavender200 else Color.Gray
    )
}

