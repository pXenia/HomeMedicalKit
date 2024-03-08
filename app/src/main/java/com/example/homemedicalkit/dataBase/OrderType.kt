package com.example.homemedicalkit.dataBase

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}