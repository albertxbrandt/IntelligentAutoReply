package com.idislikeyou.intelligentautoreply.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idislikeyou.intelligentautoreply.models.ChatMessageResponse
import java.lang.IndexOutOfBoundsException

@Composable
fun ChatTextBox(chatMessageResponse: ChatMessageResponse) {
    var response = ""

    try {
        response = chatMessageResponse.content[0].text.value
    } catch (e: IndexOutOfBoundsException) {
        return
    }

    Box(modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (chatMessageResponse.role == "assistant") { "Assistant" } else { "You" },
                textAlign = TextAlign.Start,
                color = if (chatMessageResponse.role == "assistant") { Color(0xD5A58FFC)
                } else { Color.LightGray },
                style = TextStyle(
                    fontSize = 14.sp,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(0f, 8.0f),
                        blurRadius = 4f
                    )
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = response,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                color = if (chatMessageResponse.role == "assistant") { Color(0xD5A58FFC)
                } else { Color.White },
                style = TextStyle(
                    fontSize = 14.sp,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(4.0f, 8.0f),
                        blurRadius = 3f
                    )
                )
            )
        }
    }
}