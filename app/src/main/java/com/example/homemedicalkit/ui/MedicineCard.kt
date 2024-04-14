package com.example.homemedicalkit.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.homemedicalkit.R
import com.example.homemedicalkit.ViewModel.AddEditMedicineEvent
import com.example.homemedicalkit.ViewModel.AddEditMedicineViewModel
import com.example.homemedicalkit.ui.theme.BlueAFC5F0
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkLavender626997
import com.example.homemedicalkit.ui.theme.DarkLavender7F88C0
import com.example.homemedicalkit.ui.theme.LavenderD1D5F0

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicineCard(navController: NavController,
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
        ActivityResultContracts.RequestPermission()){
        if (it){
            val uri = ComposeFileProvider.getImageUri(context)
            imageUri.value = uri
            cameraLauncher.launch(imageUri.value)
        }else{ Toast.makeText(context, "Необходим доступ к камере!", Toast.LENGTH_SHORT).show()} }

    val nameState = viewModel.medicineName.value
    val descriptionState = viewModel.medicineDescription.value
    val fewState = viewModel.medicineFew.value
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(LavenderD1D5F0, DarkLavender7F88C0)
                        )
                    )
                    .padding(10.dp)
            ) {
                Box(modifier = Modifier.height(290.dp)) {
                    OutlinedCard(
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .shadow(
                                elevation = 18.dp,
                                spotColor = DarkLavender626997
                            ),
                        border = BorderStroke(1.dp, Color.Gray)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(viewModel.medicineImage.value.imageUri.toUri()),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                                .paint(
                                    painter = painterResource(id = R.drawable.test_medicine)
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomEnd),
                        horizontalArrangement = Arrangement.End
                    ) {
                        FloatingActionButton(
                            containerColor = LavenderD1D5F0,
                            onClick = {
                                val permissionResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                if (permissionResult == PackageManager.PERMISSION_GRANTED ) {
                                    val uri = ComposeFileProvider.getImageUri(context)
                                    imageUri.value = uri
                                    cameraLauncher.launch(uri)
                                }
                                else{
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }},
                            shape = RoundedCornerShape(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = "CameraAlt"
                            )
                        }
                        FloatingActionButton(
                            containerColor = BlueAFC5F0,
                            onClick = {
                                viewModel.onEvent(AddEditMedicineEvent.SaveMedicine)
                                navController.navigateUp()},
                            shape = RoundedCornerShape(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done"
                            )
                        }

                    }
                }
                Column(modifier = Modifier.padding(0.dp)) {
                    DateAlertDialog(viewModel)
                    Spacer(modifier = Modifier.padding(10.dp))
                    Row() {
                        OutlinedTextField(
                            modifier = Modifier
                                .height(48.dp)
                                .width(250.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(LavenderD1D5F0),
                            shape = RoundedCornerShape(30.dp),
                            value = nameState.text,
                            onValueChange = {
                                    viewModel.onEvent(AddEditMedicineEvent.EnteredName(it))
                            },
                            placeholder = {
                                Text("Название",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontFamily = Comfortaa,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    ))
                            }
                        )
                        Checkbox(
                            checked = fewState,
                            onCheckedChange = {viewModel.onEvent(AddEditMedicineEvent.EnteredMedicineFew(it)) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = LavenderD1D5F0,
                                checkmarkColor = DarkLavender7F88C0
                            )
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
                                color = Color.Gray
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30.dp))
                            .background(LavenderD1D5F0),
                        shape = RoundedCornerShape(30.dp),
                        value = descriptionState.text,
                        onValueChange = {
                            viewModel.onEvent(AddEditMedicineEvent.EnteredDescription(it))
                        },
                        placeholder = {
                            Text("Описание",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontFamily = Comfortaa,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                ))
                        }
                    )

                }
            }
        }
    )
}

