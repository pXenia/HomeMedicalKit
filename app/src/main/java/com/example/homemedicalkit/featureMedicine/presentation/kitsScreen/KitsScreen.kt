package com.example.homemedicalkit.presentation.kitsScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.homemedicalkit.R
import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import com.example.homemedicalkit.featureMedicine.presentation.kitsScreen.components.getColorById
import com.example.homemedicalkit.presentation.util.Screen
import com.example.homemedicalkit.ui.theme.Blue1
import com.example.homemedicalkit.ui.theme.BlueContainer
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlueOutlined
import com.example.homemedicalkit.ui.theme.DarkLavender200
import com.example.homemedicalkit.ui.theme.HomeMedicalKitTheme
import com.example.homemedicalkit.ui.theme.LightBlue1


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun KitsScreen(navController: NavController,
               viewModel: KitsViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val scroll = rememberScrollState()
    HomeMedicalKitTheme {
        Scaffold(
            bottomBar = { NavigationBarSample(navController = navController) },
            topBar = {
                TopAppBar(
                    title = { },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    actions = {
                        IconButton(
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Blue1),
                            onClick = {
                                navController.navigate(
                                    Screen.KitDialog.route + "?kitId=${-1}"
                                )
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add"
                            )
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 10.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.White, Color.White)
                            )
                        )
                        .verticalScroll(scroll)
                ) {
                    Box(Modifier.background(Color.Transparent)) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(320.dp)
                                .clip(RoundedCornerShape(0, 0, 40, 40))
                                .align(Alignment.Center)
                                .graphicsLayer {
                                    alpha = 1f - (scroll.value.toFloat() / scroll.maxValue)
                                    translationY = scroll.value.toFloat()
                                },
                            painter = painterResource(id = R.drawable.first_aid_kit_bro),
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .graphicsLayer {
                                    alpha = 0f + (scroll.value.toFloat() / scroll.maxValue)
                                }
                                .padding(top = 10.dp, start = 10.dp),
                            text = "Моя аптечка",
                            style = TextStyle(
                                fontFamily = Comfortaa,
                                fontWeight = FontWeight.Bold,
                                fontSize = 36.sp,
                                color = BlueContainer
                            )
                        )
                        Icon(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .graphicsLayer {
                                    alpha = 1f - (scroll.value.toFloat() / scroll.maxValue)
                                },
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Add"
                        )
                    }
                    val heightScr = LocalConfiguration.current.screenHeightDp.dp
                    LazyVerticalGrid(
                        modifier = Modifier
                            .height(heightScr - heightScr * 0.18f)
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        columns = GridCells.Adaptive(140.dp),
                    ) {
                        items(state.kits) { kit ->
                            CardKit(kit = kit, navController = navController)
                        }
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        )
    }
}


@Composable
fun NavigationBarSample(navController: NavController) {
    var selectedItem = remember { mutableIntStateOf(0) }
    NavigationBar(
        modifier = Modifier
            .alpha(0.9f)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        containerColor = Blue1,
        tonalElevation = 10.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.CreateNewFolder, null) },
            label = { Text("Аптечки") },
            selected = selectedItem.value == 0,
            onClick = {
                selectedItem.value = 0
                navController.navigate(Screen.KitsScreen.route) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = LightBlue1)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.FormatListNumbered, null) },
            label = { Text("Лекарства") },
            selected = selectedItem.value == 1,
            onClick = {
                selectedItem.value = 1
                navController.navigate(Screen.MedicinesList.route)},
            colors = NavigationBarItemDefaults.colors(indicatorColor = LightBlue1)

        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AddCircleOutline, null) },
            label = { Text("Добавить") },
            selected = selectedItem.value == 2,
            onClick = {
                selectedItem.value = 2
                navController.navigate(
                Screen.MedicineCard.route + "?kitId=${-1}")
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = LightBlue1)

        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, null)},
            label = { Text("Профиль") },
            selected = selectedItem.value == 3,
            onClick = {
                selectedItem.value = 3
                navController.navigate(
                    Screen.UserScreen.route)
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = LightBlue1)
        )

    }
}


@Composable
fun CardKit(kit: Kit, navController : NavController){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Blue1
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
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        navController.navigate(
                            Screen.MedicinesList.route + "?kitId=${kit.kitId}"                        )

                    },
                    onLongPress = {
                        navController.navigate(
                            Screen.DeleteDialogKit.route + "?kitId=${kit.kitId}"
                        )
                    }
                )
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Canvas(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                translate(left = 0f, top = 160f) {
                    drawCircle(getColorById(kit.kitColor), radius = 55.dp.toPx())
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
                text = kit.kitName,
                style = TextStyle(
                    fontFamily = Comfortaa,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = DarkBlueOutlined
                )
            )

        }

    }
}