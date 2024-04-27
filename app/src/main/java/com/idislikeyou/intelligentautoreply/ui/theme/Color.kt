package com.idislikeyou.intelligentautoreply.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val mainItemBox = Color(0x5B636363)

val PurpleBlackGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFF2D0050),
        Color.Black
    ),
    start = Offset(
        x = 0f,
        y = 0f
    ),
    end = Offset(
        x = 2000f,
        y = 2000f
    )
)