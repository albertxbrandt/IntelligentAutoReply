package com.idislikeyou.intelligentautoreply.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.idislikeyou.intelligentautoreply.ui.elements.APIKeyInput
import com.idislikeyou.intelligentautoreply.ui.elements.ModelDropDown
import com.idislikeyou.intelligentautoreply.ui.viewmodels.WelcomeViewModel

@Composable
fun WelcomeScreen(navController: NavController, welcomeViewModel: WelcomeViewModel) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        color = Color(0x16FFFFFF)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 32.sp,
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(5.0f, 10.0f),
                            blurRadius = 4f
                        )
                    )
                )

                Text(text = "Input an OpenAI API Key to continue.", modifier = Modifier.padding(all = 8.dp))
                APIKeyInput(navController, welcomeViewModel)

                /*
                when (welcomeViewModel.stage.value) {
                    0 -> {
                        Text(text = "Input an OpenAI API Key to continue.", modifier = Modifier.padding(all = 8.dp))
                        APIKeyInput(welcomeViewModel)
                    }
                    1 -> {
                        Text(text = "Select a model to continue.", modifier = Modifier.padding(all = 8.dp))
                        ModelDropDown(welcomeViewModel)
                    }
                    2 -> {
                        Text(text = "Here's what you gave me:", modifier = Modifier.padding(all = 8.dp))
                        Text("api ${welcomeViewModel.apiKey.value}")
                        Text("model ${welcomeViewModel.model.value}")
                    }
                }

                 */

            }
        }

    }
}