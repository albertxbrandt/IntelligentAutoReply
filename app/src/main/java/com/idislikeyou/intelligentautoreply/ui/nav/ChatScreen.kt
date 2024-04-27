package com.idislikeyou.intelligentautoreply.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idislikeyou.intelligentautoreply.R
import com.idislikeyou.intelligentautoreply.ui.elements.ChatTextBox
import com.idislikeyou.intelligentautoreply.ui.elements.SquaredButton
import com.idislikeyou.intelligentautoreply.ui.elements.SquaredInput
import com.idislikeyou.intelligentautoreply.ui.theme.mainItemBox
import com.idislikeyou.intelligentautoreply.ui.viewmodels.ChatViewModel

@Composable
fun ChatScreen(chatViewModel: ChatViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                reverseLayout = true
            ) {
                when {
                    chatViewModel.chatMessageState.value.loading -> {
                        items (1) {
                            Text("Loading")
                        }
                    }

                    chatViewModel.chatMessageState.value.error != null -> {
                        items(1) {
                            Text("Oops error occured")
                        }
                    }

                    else -> {
                        items(chatViewModel.chatMessageState.value.list) {
                            ChatTextBox(chatMessageResponse = it)
                        }

                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                SquaredInput(
                    value = chatViewModel.chatMessageInputState.value.input,
                    onValueChange = { chatViewModel.onEditChatMessageInput(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(IntrinsicSize.Min),
                    enabled = !chatViewModel.chatMessageInputState.value.processing
                )
                SquaredButton(
                    modifier = Modifier.height(56.dp),
                    onClick = {
                        chatViewModel.sendChatMessage()
                        chatViewModel.clearChatMessageInput()
                    }) {
                    Icon(painterResource(R.drawable.baseline_send_24),contentDescription = null)
                }
            }

        }
    }

}
