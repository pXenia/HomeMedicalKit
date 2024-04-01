package com.example.homemedicalkit.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.homemedicalkit.ViewModel.AddEditMedicineEvent
import com.example.homemedicalkit.ViewModel.AddEditMedicineViewModel
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlue
import com.example.homemedicalkit.ui.theme.LightBlue1
import com.example.homemedicalkit.ui.theme.LightBlue2
import com.example.homemedicalkit.ui.theme.Orange80
import com.example.homemedicalkit.ui.tools.DateTransformation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineShow(
    navController: NavController,
    viewModel: AddEditMedicineViewModel = hiltViewModel()
) {
    val nameState = viewModel.medicineName.value
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp),
                containerColor = DarkBlue,
                contentColor = LightBlue1,
                onClick = {
                    viewModel.onEvent(AddEditMedicineEvent.SaveMedicine)
                    navController.navigateUp()},
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Check, "Add")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBlue),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(330.dp)
                        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                        .background(LightBlue1),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LargeFloatingActionButton(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.End)
                            .padding(5.dp)
                            .border(
                                width = 0.5.dp,
                                shape = CircleShape,
                                color = DarkBlue,
                            ),
                        containerColor = LightBlue1,
                        onClick = { },
                        shape = CircleShape
                    ) {
                        Icon(Icons.Filled.Menu, "Menu")
                    }
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.test_medicine),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(20.dp)
                            .size(300.dp, 200.dp)
                            .clip(
                                RoundedCornerShape(30.dp)
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    value = nameState.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditMedicineEvent.EnteredName(it))
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
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DateTimeField(viewModel)
                    CheckBoxCast(viewModel)
                    Text(
                        text = "Осталось мало",
                        style = TextStyle(
                            fontFamily = Comfortaa,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = LightBlue2
                        ),
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                LazyRow(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    items(10) {
                        TagElement()
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                DescriptionMedicine(viewModel)
            }
        }
    )

}

@Composable
fun TagElement() {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Orange80
        ),
        modifier = Modifier
            .height(35.dp)
            .clip(RoundedCornerShape(30.dp)),
    ){
        Row(Modifier.padding(7.dp)){
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription ="" )
            Spacer(modifier = Modifier.padding(2.dp))
            Text("Жаропониж")
            Spacer(modifier = Modifier.padding(2.dp))
        }
    }
}
@Composable
fun DescriptionMedicine(viewModel: AddEditMedicineViewModel
) {
    val descriptionState = viewModel.medicineDescription.value
    BasicTextField(
        modifier = Modifier
            .fillMaxSize(),
        value = descriptionState.text,
        onValueChange = {
            viewModel.onEvent(AddEditMedicineEvent.EnteredDescription(it))
        },
        textStyle = TextStyle(
            fontSize = 15.sp
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(LightBlue1)

            ) {
                Box(modifier = Modifier.padding(10.dp)) {
                    innerTextField.invoke()
                }
            }
        }
    )
}
@Composable
fun DateTimeField(viewModel: AddEditMedicineViewModel) {
    val dataState = viewModel.medicineDate.value

    Box(modifier = Modifier
        .height(50.dp)
        .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(LightBlue1)){
        BasicTextField(
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center),
            value = dataState.text,
            onValueChange = {viewModel.onEvent(AddEditMedicineEvent.EnteredDate(it))},
            visualTransformation = DateTransformation(),
            singleLine = true)
    }
}
@Composable
fun CheckBoxCast(viewModel: AddEditMedicineViewModel) {
    val fewState = viewModel.medicineFew.value
    Checkbox(
        checked = fewState,
        onCheckedChange = { viewModel.onEvent(AddEditMedicineEvent.EnteredMedicineFew(it)) },
        colors = CheckboxDefaults.colors(checkedColor = LightBlue2, checkmarkColor = DarkBlue)
    )
}


