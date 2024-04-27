package com.idislikeyou.intelligentautoreply.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idislikeyou.intelligentautoreply.dataStore
import com.idislikeyou.intelligentautoreply.models.Assistant
import com.idislikeyou.intelligentautoreply.util.AppSettings
import com.idislikeyou.intelligentautoreply.util.chatService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(val dataStore: DataStore<AppSettings>) : ViewModel() {
    private val _activeApiKey = mutableStateOf("")

    private val _listOfAssistants = mutableStateOf(AssistantState())
    val listOfAssistants: State<AssistantState> = _listOfAssistants

    init {
        viewModelScope.launch {
            try {

                val response = chatService.fetchAssistants("Bearer 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")

               _listOfAssistants.value = _listOfAssistants.value.copy(
                   list = response.data,
                   loading = false,
                   error = null
               )

            } catch (e: Exception) {
                _listOfAssistants.value = _listOfAssistants.value.copy(
                    loading = false,
                    error = "Error fetching assistants ${e.message}"
                )
            }
        }
    }

    suspend fun onAssistantClick(assistant: Assistant) {
        dataStore.updateData {
            it.copy(
                selectedAssistantId = assistant.id
            )
        }

    }

}



data class AssistantState(
    val loading: Boolean = true,
    val list: List<Assistant> = emptyList(),
    val error: String? = null
)