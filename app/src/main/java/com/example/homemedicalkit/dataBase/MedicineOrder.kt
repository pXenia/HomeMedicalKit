package com.example.homemedicalkit.dataBase

sealed class MedicineOrder(val orderType: OrderType) {

    class Name(orderType: OrderType): MedicineOrder(orderType)
    class Date(orderType: OrderType): MedicineOrder(orderType)
    class Color(orderType: OrderType): MedicineOrder(orderType)
}