package com.example.homemedicalkit.featureMedicine.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}