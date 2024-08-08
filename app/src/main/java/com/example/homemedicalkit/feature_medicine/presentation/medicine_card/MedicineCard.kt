package com.example.homemedicalkit.presentation.medicineCard

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.homemedicalkit.R
import com.example.homemedicalkit.presentation.medicineCard.components.DateAlertDialog
import com.example.homemedicalkit.presentation.medicineCard.imageTools.ComposeFileProvider
import com.example.homemedicalkit.ui.theme.Blue1
import com.example.homemedicalkit.ui.theme.Blue2
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlueOutlined
import com.example.homemedicalkit.ui.theme.HomeMedicalKitTheme
import com.example.homemedicalkit.ui.theme.LightBlue2

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicineCard(navController: NavHostController,
                 viewModel: AddEditMedicineViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var imageUri = remember {
        mutableStateOf(viewModel.medicineImage.value.imageUri.toUri())
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            viewModel.onEvent(AddEditMedicineEvent.EnteredImage(imageUri.value.toString()))
        }
    )
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            val uri = ComposeFileProvider.getImageUri(context)
            imageUri.value = uri
            cameraLauncher.launch(imageUri.value)
        } else {
            Toast.makeText(context, "Необходим доступ к камере!", Toast.LENGTH_SHORT).show()
        }
    }

    val nameState = viewModel.medicineName.value
    val descriptionState = viewModel.medicineDescription.value
    val fewState = viewModel.medicineFew.value
    val heightScr = LocalConfiguration.current.screenHeightDp.dp
    val heightNav = LocalConfiguration.current.navigation.dp
    val kitsNames = viewModel.stateKits
    val kitsIsEmpty = kitsNames.isEmpty()

    val scroll = rememberScrollState()
    HomeMedicalKitTheme {
        Scaffold(
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
                        .fillMaxHeight()
                        .height(800.dp)
                        .verticalScroll(scroll)
                ) {
                    Box(modifier = Modifier
                        .graphicsLayer {
                            translationY = 0.25f * scroll.value.toFloat()
                        }
                        .height(300.dp)) {
                        Box(
                            Modifier
                                .height(280.dp)
                                .fillMaxWidth()
                                .background(Blue1)
                                .graphicsLayer {
                                    alpha = 1f - 0.35f * (scroll.value.toFloat() / scroll.maxValue)
                                }
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    viewModel.medicineImage.value.imageUri.toUri(),
                                    contentScale = ContentScale.Crop),
                                contentDescription = "",
                                modifier = Modifier
                                    .paint(
                                        painter = painterResource(id = R.drawable.pharmacist_bro),
                                        contentScale = ContentScale.FillBounds

                                    ),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer {
                                    translationY = -0.2f * scroll.value.toFloat()
                                }
                                .align(Alignment.BottomEnd),
                            horizontalArrangement = Arrangement.End
                        ) {
                            FloatingActionButton(
                                containerColor = Blue2,
                                onClick = {
                                    val permissionResult = ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.CAMERA
                                    )
                                    if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                                        val uri = ComposeFileProvider.getImageUri(context)
                                        imageUri.value = uri
                                        cameraLauncher.launch(uri)
                                    } else {
                                        permissionLauncher.launch(Manifest.permission.CAMERA)
                                    }
                                },
                                shape = CircleShape
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CameraAlt,
                                    contentDescription = "CameraAlt"
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            FloatingActionButton(
                                containerColor = LightBlue2,
                                onClick = {
                                    if (!kitsIsEmpty) {
                                        viewModel.onEvent(AddEditMedicineEvent.SaveMedicine)
                                        navController.navigateUp()
                                    }
                                    else{
                                        Toast.makeText(context,"Сначала добавьте аптечку!", Toast.LENGTH_LONG).show()
                                    }
                                },
                                shape = CircleShape
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row() {
                            DateAlertDialog(viewModel)
                            Spacer(modifier = Modifier.padding(5.dp))
                            DropdownMenuBox(viewModel)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Row {
                            OutlinedTextField(
                                modifier = Modifier
                                    .height(52.dp)
                                    .width(250.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color.Transparent),
                                shape = RoundedCornerShape(20.dp),
                                singleLine = true,
                                value = nameState,
                                onValueChange = {
                                    viewModel.onEvent(AddEditMedicineEvent.EnteredName(it))
                                },
                                placeholder = {
                                    Text(
                                        "Название",
                                        style = TextStyle(
                                            textAlign = TextAlign.Center,
                                            fontFamily = Comfortaa,
                                            fontWeight = FontWeight.ExtraBold,

                                        )
                                    )
                                }
                            )
                            Checkbox(
                                checked = fewState,
                                onCheckedChange = {
                                    viewModel.onEvent(
                                        AddEditMedicineEvent.EnteredMedicineFew(
                                            it
                                        )
                                    )
                                },
                            )
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                text = "Мало",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontFamily = Comfortaa,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    color = DarkBlueOutlined
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        OutlinedTextField(
                            modifier = Modifier
                                .height(heightScr * 0.4f)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.Transparent),
                            shape = RoundedCornerShape(20.dp),
                            value = descriptionState,
                            maxLines = 14,
                            onValueChange = {
                                viewModel.onEvent(AddEditMedicineEvent.EnteredDescription(it))
                            },
                            textStyle = TextStyle(
                                fontFamily = Comfortaa,
                                fontSize = 16.sp,
                            ),
                            placeholder = {
                                Text(
                                    "Описание",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontFamily = Comfortaa,
                                        fontWeight = FontWeight.ExtraBold,
                                    )
                                )
                            }
                        )
                        Spacer(modifier = Modifier.padding(heightNav + 35.dp))

                    }
                }
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(viewModel: AddEditMedicineViewModel) {

    val kitsNames = viewModel.stateKits
    val isKitsEmpty = kitsNames.isEmpty()
    var expanded = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
        }
    ) {
        OutlinedTextField(
            enabled = !isKitsEmpty,
            value = kitsNames[viewModel.medicineKit.value] ?: "",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { TrailingIcon(expanded = expanded.value) },
            singleLine = true,
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Blue1)
                .menuAnchor(),
            shape = RoundedCornerShape(20.dp),
        )

        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            kitsNames.forEach { item ->
                DropdownMenuItem(
                    text = { Text(
                        text = item.value,
                        ) },
                    onClick = {
                        viewModel.onEvent(AddEditMedicineEvent.EnteredKit(item.key))
                        expanded.value = false
                    }
                )
            }
        }
    }
}