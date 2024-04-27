package com.idislikeyou.intelligentautoreply.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.idislikeyou.intelligentautoreply.util.AppSettings

class WelcomeViewModel(val dataStore: DataStore<AppSettings>) : ViewModel() {
    private val _stage = mutableIntStateOf(0)
    private val _apiKey = mutableStateOf("")
    private val _model = mutableStateOf("")

    val stage: MutableState<Int> = _stage
    val apiKey = _apiKey
    val model = _model

    fun nextStage() {
        _stage.value++
    }

    fun setApiKey(apiKey: String) {
        _apiKey.value = apiKey
    }

    fun setModel(model: String) {
        _model.value = model
    }
}