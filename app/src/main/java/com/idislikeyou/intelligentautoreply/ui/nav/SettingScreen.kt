package com.idislikeyou.intelligentautoreply.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idislikeyou.intelligentautoreply.ui.elements.SquaredInput

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Settings",
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 16.dp,bottom = 16.dp
                ), color = Color.White
            )

            SquaredInput(value = "", onValueChange = {})


        }



    }
}