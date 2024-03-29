package com.example.homemedicalkit.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homemedicalkit.R
import com.example.homemedicalkit.ViewModel.MedicalKitViewModel
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlue
import com.example.homemedicalkit.ui.theme.LightBlue1
import com.example.homemedicalkit.ui.theme.LightBlue2


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineListElements(medKitName: String = "Моя аптечка",
                         viewModel: MedicalKitViewModel = hiltViewModel(),
                         navController: NavController) {
    val state = viewModel.state.value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp),
                containerColor = DarkBlue,
                contentColor = LightBlue1,
                onClick = { navController.navigate(Screen.AddEditMedicineScreen.route)},
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightBlue1),
            ) {
                HeadPage()
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    items(state.medicines.size) { medicine ->
                        MedicineElementCast(state.medicines[medicine], navController)
                    }
                }

            }
        }
    )
}

@Composable
fun HeadPage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(DarkBlue)
    ) {
        LargeFloatingActionButton(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.End)
                .padding(5.dp),
            containerColor = LightBlue1,
            onClick = { },
            shape = CircleShape
        ) {
            Icon(Icons.Filled.Menu, "Menu")
        }
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Моя аптечка",
            style = TextStyle(
                fontFamily = Comfortaa,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                color = LightBlue2
            )
        )
        TextFieldCast()

    }

}

@Composable
fun TextFieldCast() {
    var textInp = remember { mutableStateOf("") }
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        value = textInp.value,
        onValueChange = {
            textInp.value = it
        },
        textStyle = TextStyle(
            fontSize = 15.sp
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(LightBlue1)
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = "",
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    innerTextField.invoke()
                }
            }
        }
    )
}

@Composable
fun MedicineElementCast(medicine: Medicine,
                        navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = LightBlue2
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(30.dp))
            .clickable { navController.navigate(
                Screen.AddEditMedicineScreen.route +
                        "?noteId=${medicine.medicineId}") },
    ) {
        Row {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.test_medicine),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = medicine.medicineName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Comfortaa,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = medicine.medicineDate,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = Comfortaa
                    )
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }


    }
}
