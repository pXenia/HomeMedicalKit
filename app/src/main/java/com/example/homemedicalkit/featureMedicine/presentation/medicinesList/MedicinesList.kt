package com.example.homemedicalkit.presentation.medicinesList

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import com.example.homemedicalkit.presentation.kitsScreen.NavigationBarSample
import com.example.homemedicalkit.presentation.medicinesList.components.SortParam
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.Blue1
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkLavender200
import com.example.homemedicalkit.ui.theme.HomeMedicalKitTheme
import com.example.homemedicalkit.ui.theme.RedContainerDate
import com.example.homemedicalkit.ui.theme.YellowContainerFew
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicinesList(viewModel: MedicinesViewModel = hiltViewModel(),
                  navController: NavController) {
    val state = viewModel.state.value
    val scroll = rememberScrollState()
    val heightScr = LocalConfiguration.current.screenHeightDp.dp
    val heightNav = LocalConfiguration.current.navigation.dp

    HomeMedicalKitTheme {
        Scaffold(
            bottomBar = { NavigationBarSample(navController = navController, selectedItem = 1) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.MedicineCard.route + "?kitId=${viewModel.kitId}") },
                    shape = CircleShape
                ) {
                    Icon(Icons.Filled.Add, "Add")
                }
            },
            topBar = {
                TopAppBar(
                    title = { },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(scroll)
                ) {
                    Box(Modifier.graphicsLayer {
                    }) {
                        Image(
                            modifier = Modifier
                                .height(140.dp)
                                .graphicsLayer {
                                    alpha = 1f - (scroll.value.toFloat() / scroll.maxValue) }
                                .align(Alignment.TopEnd),
                            painter = painterResource(id = R.drawable.medicine_amico),
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 20.dp)
                                .graphicsLayer {
                                    alpha = 1f - (scroll.value.toFloat() / scroll.maxValue)

                                }
                                .fillMaxWidth(),
                            text = viewModel.kitName.value,
                            style = TextStyle(
                                shadow = Shadow(DarkLavender200, blurRadius = 2f),
                                fontFamily = Comfortaa,
                                fontWeight = FontWeight.Bold,
                                fontSize = 34.sp,
                                color = DarkLavender200
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    SortParam(
                        onOrderChange = { viewModel.onEvent(MedicineEvent.Order(it)) },
                        medicineOrder = state.medicineOrder
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightScr * 0.75f),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(
                            items = state.medicines,
                            key = { medicine -> medicine.medicineId ?: -1 }
                        ) { medicine ->
                            MedicineCardSmall(
                                medicine = medicine,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(heightNav + 35.dp))
                }
            }
        )
    }
}
@SuppressLint("SimpleDateFormat")
@Composable
fun MedicineCardSmall(medicine: Medicine, navController: NavController, viewModel: MedicinesViewModel) {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val toggle = remember { mutableStateOf(false) }
    val animatedPadding = animateDpAsState(targetValue = if (toggle.value) 5.dp else 0.dp,
        label = "padding",
    )
    val color =
        if (medicine.medicineDate < System.currentTimeMillis())
        { RedContainerDate }
        else if (medicine.medicineNumberFew)
        { YellowContainerFew }
        else Blue1
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(animatedPadding.value)
            .fillMaxWidth()
            .height(120.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        navController.navigate(
                            Screen.MedicineCard.route + "?medicineId=${medicine.medicineId}"
                        )

                    },
                    onPress = {toggle.value = false},
                    onLongPress = {
                        toggle.value = true
                        navController.navigate(
                            Screen.DeleteDialog.route + "?medicineId=${medicine.medicineId}"
                        )
                    }
                )
            },
        colors = CardDefaults.cardColors(color),
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
                    .paint(painter = painterResource(id = R.drawable.thermometer)),
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
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = sdf.format(medicine.medicineDate),
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontFamily = Comfortaa,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

