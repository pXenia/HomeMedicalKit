package com.example.homemedicalkit.presentation.medicinesList

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.homemedicalkit.R
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.presentation.medicinesList.components.SortParam
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.BlueAFC5F0
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkLavender100
import com.example.homemedicalkit.ui.theme.DarkLavender200
import com.example.homemedicalkit.ui.theme.LavenderD1D5F0
import com.example.homemedicalkit.ui.theme.WhiteEAEBEC
import java.text.SimpleDateFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicinesList(viewModel: MedicinesViewModel = hiltViewModel(),
                  navController: NavController){
    val state = viewModel.state.value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp),
                containerColor = BlueAFC5F0,
                contentColor = Color.Gray,
                onClick = { navController.navigate(Screen.MedicineCard.route+"?kitId=${viewModel.kitId}")},
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(LavenderD1D5F0, WhiteEAEBEC)
                        )
                    )
                    .padding(10.dp)
            ) {
                Canvas(modifier = Modifier.align(Alignment.End)) {
                    translate(left = -200f, top = -10f) {
                        drawCircle(DarkLavender100, radius = 40.dp.toPx())
                    }
                    translate(left = -40f, top = 50f) {
                        drawCircle(BlueAFC5F0, radius = 60.dp.toPx())
                    }
                }
                Canvas(modifier = Modifier.align(Alignment.Start)) {
                    translate(left = 0f, top = -40f) {
                        drawCircle(LavenderD1D5F0, radius = 40.dp.toPx())
                    }
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 5.dp),
                    onClick = {navController.navigateUp()}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Add"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 30.dp, top = 8.dp)
                        .fillMaxWidth(),
                    text = viewModel.kitName.value,
                    style = TextStyle(
                        shadow = Shadow(DarkLavender200, blurRadius = 2f),
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = DarkLavender200
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(LavenderD1D5F0),
                    shape = RoundedCornerShape(30.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Найти ... ",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontFamily = Comfortaa,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Search, "Search", tint = Color.Gray)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                SortParam(onOrderChange =  {viewModel.onEvent(MedicineEvent.Order(it))}, medicineOrder = state.medicineOrder)
                Spacer(modifier = Modifier.height(40.dp))
                LazyColumn( verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(state.medicines){
                            medicine ->
                        MedicineCardSmall(medicine = medicine, kit = viewModel.kitId ?: -1, navController = navController)
                    }
                }
            }
        }
    )
}
@SuppressLint("SimpleDateFormat")
@Composable
fun MedicineCardSmall(medicine: Medicine, kit: Int, navController: NavController) {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .shadow(
                elevation = 18.dp,
                spotColor = WhiteEAEBEC,
                shape = RoundedCornerShape(30.dp)
            )
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                navController.navigate(
                    Screen.MedicineCard.route + "?medicineId=${medicine.medicineId}"
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = LavenderD1D5F0
        ),
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = medicine.medicineImage.toUri()),
                contentDescription = "Photo",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .paint(painter = painterResource(id = R.drawable.test_medicine))
                    .shadow(elevation = 10.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(10.dp)
            ) {
                Text(
                    text = medicine.medicineName,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Comfortaa,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = sdf.format(medicine.medicineDate),
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Comfortaa,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}