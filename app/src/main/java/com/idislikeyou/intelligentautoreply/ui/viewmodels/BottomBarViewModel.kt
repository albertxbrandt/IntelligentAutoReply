package com.idislikeyou.intelligentautoreply.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BottomBarViewModel : ViewModel() {
    var currentRoute by mutableStateOf("")
        private set

    fun updateRoute(route: String) {
        currentRoute = route
    }
}

