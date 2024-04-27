package com.idislikeyou.intelligentautoreply.ui.elements

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.idislikeyou.intelligentautoreply.ui.nav.Screen
import com.idislikeyou.intelligentautoreply.ui.nav.getRoute
import com.idislikeyou.intelligentautoreply.ui.viewmodels.BottomBarViewModel

@Composable
fun BottomBar(
    navController: NavController,
    bottomBarViewModel: BottomBarViewModel,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier.height(64.dp),
        containerColor = Color(0xFF141414),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            screensInBottom.forEach {
                item ->
                Button(
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xD5FFFFFF)
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    onClick = {
                        if (bottomBarViewModel.currentRoute != item.route) {
                            bottomBarViewModel.updateRoute(item.route)
                            navController.navigate(item.route)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            id = item.icon
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint =
                            if (bottomBarViewModel.currentRoute == item.route) {
                                Color(0xD5B6A2FF)
                            } else {
                                Color(0xD5FFFFFF)

                        }
                    )
                }
            }
        }
    }
}

val screensInBottom = listOf(
    Screen.Settings,
    Screen.Home,
    Screen.ChatScreen
)