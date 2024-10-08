package com.example.homemedicalkit.feature_medicine.domain.util

sealed class MedicineOrder(val orderType: OrderType) {

    class Name(orderType: OrderType): MedicineOrder(orderType)
    class Date(orderType: OrderType): MedicineOrder(orderType)
    class Few(orderType: OrderType): MedicineOrder(orderType)

    fun copy(orderType: OrderType): MedicineOrder {
        return when(this){
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Few -> Few(orderType)
        }
    }
}
