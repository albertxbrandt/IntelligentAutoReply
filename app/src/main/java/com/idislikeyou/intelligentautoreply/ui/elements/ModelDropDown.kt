package com.idislikeyou.intelligentautoreply.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idislikeyou.intelligentautoreply.ui.viewmodels.WelcomeViewModel

@Composable
fun ModelDropDown(welcomeViewModel: WelcomeViewModel) {
    val gptVersion = arrayOf("gpt-3.5-turbo", "gpt-4", "gpt-4-turbo-preview")

    gptVersion.forEach {
        SquaredButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            onClick = {
                welcomeViewModel.setModel(it)
                welcomeViewModel.nextStage()
            }
        ) {
            Text(it)
        }
    }
}