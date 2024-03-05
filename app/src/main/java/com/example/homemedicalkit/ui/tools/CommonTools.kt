package com.example.homemedicalkit.ui.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homemedicalkit.ui.theme.DarkBlue
import com.example.homemedicalkit.ui.theme.LightBlue1
import com.example.homemedicalkit.ui.theme.LightBlue2

class CommonTools {
    @Preview(showSystemUi = true)
    @Composable
    fun DateTimeField(){
        val text = remember {
            mutableStateOf("01022010")
        }
        Box(modifier = Modifier
            .height(50.dp)
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(LightBlue1)){
            BasicTextField(
                modifier = Modifier.padding(10.dp),
                textStyle = TextStyle(textAlign = TextAlign.Center),
                value = text.value,
                onValueChange = {text.value = it},
                visualTransformation = DateTransformation(),
                singleLine = true)
        }
    }
    @Composable
    fun CheckBoxCast() {
        val checkedState = remember { mutableStateOf(true) }
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = CheckboxDefaults.colors(checkedColor = LightBlue2, checkmarkColor = DarkBlue)
        )
    }
}
