package com.example.homemedicalkit.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homemedicalkit.R
import com.example.homemedicalkit.ui.theme.BlueAFC5F0
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkLavender200
import com.example.homemedicalkit.ui.theme.DarkLavender100
import com.example.homemedicalkit.ui.theme.LavenderC4C9F0
import com.example.homemedicalkit.ui.theme.LavenderD1D5F0
import com.example.homemedicalkit.ui.theme.Red80
import com.example.homemedicalkit.ui.theme.WhiteEAEBEC

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun KitsScreen(){
    Scaffold(
        bottomBar = { NavigationBarSample() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(WhiteEAEBEC, LavenderD1D5F0)
                        )
                    )
            ){
                Canvas(modifier = Modifier.align(Alignment.End)) {
                    translate(left = -200f, top = 20f) {
                        drawCircle(DarkLavender100, radius = 80.dp.toPx())}
                    translate(left = -100f, top = 220f) {
                        drawCircle(LavenderD1D5F0, radius = 70.dp.toPx())}
                    translate(left = -40f, top =50f) {
                        drawCircle(BlueAFC5F0, radius = 60.dp.toPx())
                    }
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp),
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Add"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 30.dp, top = 10.dp )
                        .fillMaxWidth(),
                    text = "Моя\nаптечка",
                    style = TextStyle(
                        shadow = Shadow(DarkLavender200, blurRadius = 2f),
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.Bold,
                        fontSize = 48.sp,
                        color = DarkLavender100
                    )
                )
                Spacer(modifier = Modifier.height(70.dp))
                LazyHorizontalGrid(
                    modifier = Modifier.padding(20.dp).height(420.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    rows = GridCells.Adaptive(160.dp),
                ) {
                    items(5) { photo ->
                        CardKit()
                    }
                }
            }
        }
    )
}
@Composable
fun NavigationBarSample() {
    var selectedItem = remember { mutableIntStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    NavigationBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(30.dp))
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Add, "Add") },
                label = { Text(item) },
                selected = selectedItem.value == index,
                onClick = { selectedItem.value = index }
            )
        }
    }
}


@Composable
fun CardKit(){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = LavenderC4C9F0
        ),
        modifier = Modifier
            .shadow(
                elevation = 18.dp,
                spotColor = DarkLavender200,
                shape = RoundedCornerShape(40.dp)
            )
            .height(200.dp)
            .width(180.dp)
            .clip(RoundedCornerShape(40.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Canvas(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                translate(left = 0f, top = 160f) {
                    drawCircle(Red80, radius = 55.dp.toPx())
                }
            }
            Image(
                bitmap = ImageBitmap.imageResource( R.drawable.drugs),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(30.dp)
                    .width(60.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .width(140.dp)
                    .height(36.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Детское",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = Comfortaa,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = DarkLavender100
                )
            )

        }

    }
}