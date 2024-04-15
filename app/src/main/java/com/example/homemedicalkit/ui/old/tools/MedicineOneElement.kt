package com.example.homemedicalkit.ui.old.tools
/*
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
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
import com.example.homemedicalkit.ui.theme.Comfortaa
import com.example.homemedicalkit.ui.theme.DarkBlue
import com.example.homemedicalkit.ui.theme.LightBlue1
import com.example.homemedicalkit.ui.theme.LightBlue2
import com.example.homemedicalkit.ui.theme.Orange80
import com.example.homemedicalkit.ui.tools.DateTransformation
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineShow(
    navController: NavController,
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
        if (it) {
            val uri = ComposeFileProvider.getImageUri(context)
            imageUri.value = uri
            cameraLauncher.launch(imageUri.value)
        }
        else{Toast.makeText(context, "Необходим доступ к камере!", Toast.LENGTH_SHORT).show()}
    }


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
                        onClick = {

                        },
                        shape = CircleShape
                    ) {
                        Icon(Icons.Filled.Menu, "Menu")
                    }
                    Image(
                        painter = rememberAsyncImagePainter(viewModel.medicineImage.value.imageUri.toUri()),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(20.dp)
                            .size(300.dp, 200.dp)
                            .clip(
                                RoundedCornerShape(30.dp)
                            )
                            .paint( painter = painterResource(id = R.drawable.test_medicine)
                            )
                            .clickable {
                                val permissionResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                if (permissionResult == PackageManager.PERMISSION_GRANTED ) {
                                    val uri = ComposeFileProvider.getImageUri(context)
                                    imageUri.value = uri
                                    cameraLauncher.launch(uri)
                                }
                                else{
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            },
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
            value = dataState.toString(),
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
*/