package com.example.homemedicalkit.ui

import android.media.audiofx.AudioEffect.Descriptor
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.homemedicalkit.R
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlue
import com.example.homemedicalkit.ui.theme.LightBlue1
import com.example.homemedicalkit.ui.theme.LightBlue2
import com.example.homemedicalkit.ui.theme.Orange80

class MedicineOneElement {
    @Preview(showSystemUi = true)
    @Composable
    fun MedicineShow() {
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
            MedicinesListOneKit().TextFieldCast()
            Spacer(modifier = Modifier.padding(8.dp))
            MedicinesListOneKit().TextFieldCast() // do Date
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
            DescriptionMedicine()

        }

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
    fun DescriptionMedicine(){
        var textInp = remember { mutableStateOf("Описание ...") }
        BasicTextField(
            modifier = Modifier
                .fillMaxSize(),
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

}