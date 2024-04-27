package com.idislikeyou.intelligentautoreply.ui.nav

import android.content.Context
import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.idislikeyou.intelligentautoreply.dataStore
import com.idislikeyou.intelligentautoreply.ui.elements.BottomBar
import com.idislikeyou.intelligentautoreply.ui.viewmodels.BottomBarViewModel
import com.idislikeyou.intelligentautoreply.ui.viewmodels.ChatViewModel
import com.idislikeyou.intelligentautoreply.ui.viewmodels.HomeViewModel
import com.idislikeyou.intelligentautoreply.ui.viewmodels.WelcomeViewModel
import com.idislikeyou.intelligentautoreply.util.viewModelFactory

@Composable
fun Navigation(navController: NavController = rememberNavController()) {

    var showBottomBar by remember { mutableStateOf(true) }
    val welcomeViewModel = viewModel<WelcomeViewModel>(
        factory = viewModelFactory { WelcomeViewModel(navController.context.dataStore) }
    )

    val homeViewModel = viewModel<HomeViewModel>(
        factory = viewModelFactory { HomeViewModel(navController.context.dataStore) }
    )
    val chatViewModel= viewModel<ChatViewModel>(
        factory = viewModelFactory { ChatViewModel(navController.context.dataStore) }
    )
    val bottomBarViewModel: BottomBarViewModel = viewModel()


    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    navController = navController,
                    bottomBarViewModel = bottomBarViewModel
                )
            }
        }
    ) {
        pv ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = pv.calculateBottomPadding()
                ).background(Color(0x16FFFFFF))
        ){
            NavHost(
                navController = navController as NavHostController,
                startDestination = Screen.Home.route.also { bottomBarViewModel.updateRoute(it) },
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ){
                composable(Screen.Welcome.route) {
                    showBottomBar = false
                    WelcomeScreen(navController, welcomeViewModel)
                }

                composable(Screen.Home.route) {
                    showBottomBar = true
                    HomeScreen(homeViewModel)
                }

                composable(Screen.Settings.route) {
                    showBottomBar = true
                    SettingScreen()
                }

                composable(Screen.ChatScreen.route) {
                    showBottomBar = true
                    ChatScreen(chatViewModel = chatViewModel)
                }
            }
        }


    }

}

fun NavController.getRoute(): String? {
    return this.currentDestination?.route
}