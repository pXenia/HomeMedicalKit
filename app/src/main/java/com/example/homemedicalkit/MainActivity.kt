
package com.example.homemedicalkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.homemedicalkit.ui.MedicinesListOneKit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicinesListOneKit().MedicineListElements()

        }
    }
}