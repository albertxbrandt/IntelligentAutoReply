package com.idislikeyou.intelligentautoreply.ui.elements

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.idislikeyou.intelligentautoreply.dataStore
import com.idislikeyou.intelligentautoreply.ui.nav.Screen
import com.idislikeyou.intelligentautoreply.ui.theme.mainItemBox
import com.idislikeyou.intelligentautoreply.ui.viewmodels.WelcomeViewModel
import com.idislikeyou.intelligentautoreply.util.Constants.INVALID_KEY
import com.idislikeyou.intelligentautoreply.util.Constants.VALIDATE_KEY
import com.idislikeyou.intelligentautoreply.util.Constants.VALID_KEY
import com.idislikeyou.intelligentautoreply.util.chatService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun APIKeyInput(navController: NavController, welcomeViewModel: WelcomeViewModel) {
    val context = navController.context
    val scope = rememberCoroutineScope()
    //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
    var inp by remember { mutableStateOf("") }
    var buttonTextStatus by remember { mutableStateOf("Validate Key") }
    var buttonColorStatus by remember { mutableStateOf(Color.White) }

    LaunchedEffect(buttonTextStatus) {
            buttonColorStatus = when (buttonTextStatus) {
                VALID_KEY -> Color(0xFF76DF63)
                INVALID_KEY -> Color(0xFFFA5757)
                else -> Color(0xC9FFFFFF)
            }
    }

    TextField(
        value = inp,
        onValueChange = {inp = it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        shape = RoundedCornerShape(0.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors (
            unfocusedContainerColor = mainItemBox,
            focusedContainerColor = mainItemBox,
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color(0xFFFFFFFF),
            cursorColor = Color(0xFFFFFFFF),
        )
    )

    SquaredButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColorStatus,
        ),
        onClick = {
            scope.launch {
                try {
                    val validation = chatService.validateKey("Bearer $inp")
                    buttonTextStatus = VALID_KEY
                    delay(500)
                    welcomeViewModel.setApiKey(inp)
                    navController.navigate(Screen.Home.route)
                    //welcomeViewModel.nextStage()
                }catch (e: Exception) {
                    setApiKey(context, inp)
                    buttonTextStatus = INVALID_KEY
                    delay(1000)
                    buttonTextStatus = VALIDATE_KEY
                }

            }
        }
    ) {
        Text(text = buttonTextStatus)
    }
}

suspend fun setApiKey(context: Context, key: String) {
    context.dataStore.updateData { it.copy(selectedApiKey = key) }
}