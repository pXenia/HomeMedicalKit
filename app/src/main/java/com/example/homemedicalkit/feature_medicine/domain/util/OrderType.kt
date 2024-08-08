package com.example.homemedicalkit.feature_medicine.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}