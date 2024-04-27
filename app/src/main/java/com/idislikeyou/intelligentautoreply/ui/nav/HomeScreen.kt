package com.idislikeyou.intelligentautoreply.ui.nav

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idislikeyou.intelligentautoreply.ui.viewmodels.HomeViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.idislikeyou.intelligentautoreply.models.Assistant
import com.idislikeyou.intelligentautoreply.models.ChatMessage
import com.idislikeyou.intelligentautoreply.models.CreateAssistant
import com.idislikeyou.intelligentautoreply.models.CreateRun
import com.idislikeyou.intelligentautoreply.ui.elements.AssistantCard
import com.idislikeyou.intelligentautoreply.ui.elements.SquaredButton
import com.idislikeyou.intelligentautoreply.util.ApiService
import com.idislikeyou.intelligentautoreply.util.chatService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    var threadId by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Good Afternoon",
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 16.dp,bottom = 16.dp
                ), color = Color.White
            )

            when {
                homeViewModel.listOfAssistants.value.loading -> {
                    Text("Loading")
                }

                homeViewModel.listOfAssistants.value.error != null -> {
                    Text("Oops error occured")
                }

                else -> {
                    LazyColumn {
                            items(homeViewModel.listOfAssistants.value.list) {
                                AssistantCard(homeViewModel, it)
                            }
                        }


                }

            }

        }



    }
}