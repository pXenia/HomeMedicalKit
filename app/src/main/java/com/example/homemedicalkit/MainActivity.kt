@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.homemedicalkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicinePage()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun MedicinePage() {
        val teg: List<String> = listOf("Жаропонижающее", "Детское", "От зубов", "Для сна")
        Column {
            IconButton(modifier = Modifier.padding(5.dp).size(40.dp).align(Alignment.End),
                onClick = { }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Информация о приложении",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 0.dp, bottom = 40.dp, start = 20.dp, end = 20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.m_intro),
                    contentDescription = "Med",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .padding(bottom = 10.dp)
                )
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(teg.size) { item ->
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Text(
                                text = teg[item],
                                modifier = Modifier
                                    .padding(8.dp),
                                color = Color.White
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "27/02/2024",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(8.dp),
                        color = Color.White,
                    )
                }
                TextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    value = "Описание",
                    onValueChange = { },
                )

            }
        }
    }
}
