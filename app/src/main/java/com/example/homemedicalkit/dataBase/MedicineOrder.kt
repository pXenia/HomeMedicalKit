package com.example.homemedicalkit.dataBase

sealed class MedicineOrder(val orderType: OrderType) {

    class Name(orderType: OrderType): MedicineOrder(orderType)
    class Date(orderType: OrderType): MedicineOrder(orderType)
    class Color(orderType: OrderType): MedicineOrder(orderType)

    fun copy(orderType: OrderType): MedicineOrder{
        return when(this){
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
