package com.idislikeyou.intelligentautoreply.ui.nav

import androidx.annotation.DrawableRes
import com.idislikeyou.intelligentautoreply.R

sealed class Screen (
    val title: String,
    val route: String,
    @DrawableRes val icon: Int = R.drawable.no_icon_24
) {
    object Home : Screen (
        title = "Home",
        route = "home",
        icon = R.drawable.baseline_home_filled_24
    )

    object Settings : Screen (
        title = "Settings",
        route = "settings",
        icon = R.drawable.baseline_settings_24
    )

    object Welcome : Screen (
        title = "Welcome",
        route = "welcome"
    )

    object ChatScreen : Screen (
        title = "Chat with AI",
        route = "chat",
        icon = R.drawable.baseline_chat_bubble_outline_24
    )
}