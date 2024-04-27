package com.idislikeyou.intelligentautoreply.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.idislikeyou.intelligentautoreply.models.Assistant
import com.idislikeyou.intelligentautoreply.ui.theme.mainItemBox
import com.idislikeyou.intelligentautoreply.ui.viewmodels.HomeViewModel
import com.idislikeyou.intelligentautoreply.util.AppSettings
import kotlinx.coroutines.launch

@Composable
fun AssistantCard(homeViewModel: HomeViewModel, assistant: Assistant) {

    val appSettings = homeViewModel.dataStore.data.collectAsState(initial = AppSettings()).value

    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = mainItemBox,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .clickable {
                homeViewModel.viewModelScope.launch {
                    homeViewModel.onAssistantClick(assistant)
                }

            }
    ) {

        Text("${assistant.name}",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text("Model: ", fontWeight = FontWeight.Bold)
            Text(assistant.model)
        }

        Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text("ID: ", fontWeight = FontWeight.Bold)
            Text(assistant.id)
        }

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text("Instructions: ", fontWeight = FontWeight.Bold)
            Text(assistant.instructions)
        }

        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
            .fillMaxWidth()
            .background(
                if (appSettings.selectedAssistantId == assistant.id) {
                    Color.Green
                } else {
                    Color.Red
                }

            )
            .height(2.dp)) {

        }
    }
}
//353
//150