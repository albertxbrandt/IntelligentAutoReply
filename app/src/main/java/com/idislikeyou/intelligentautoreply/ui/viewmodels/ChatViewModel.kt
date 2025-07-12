package com.idislikeyou.intelligentautoreply.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idislikeyou.intelligentautoreply.models.ChatMessage
import com.idislikeyou.intelligentautoreply.models.ChatMessageResponse
import com.idislikeyou.intelligentautoreply.models.ChatThread
import com.idislikeyou.intelligentautoreply.models.CreateRun
import com.idislikeyou.intelligentautoreply.util.AppSettings
import com.idislikeyou.intelligentautoreply.util.chatService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class ChatViewModel(val dataStore: DataStore<AppSettings>) : ViewModel() {
    private val _chatMessageState = mutableStateOf(ChatViewState())
    val chatMessageState: State<ChatViewState> = _chatMessageState

    private val _chatMessageInput = mutableStateOf("")
    val chatMessageInput = _chatMessageInput

    private val _chatMessageInputState = mutableStateOf(ChatMessageState())
    val chatMessageInputState: State<ChatMessageState> = _chatMessageInputState

    private val _currentThread = mutableStateOf(ChatThread("","", 0L, emptyMap()))
    val currentThread = _currentThread

    private val _currentAssistant = mutableStateOf("")
    val currentAssistant = _currentAssistant

    init {
        viewModelScope.launch {
            dataStore.data.collect{
                setCurrentAssistant(it.selectedAssistantId)
            }
        }

        viewModelScope.launch {
            try {
                setCurrentThread(ChatThread(id = "<thread>"))
                val response = chatService.getMessages("Bearer <key>", _currentThread.value.id, limit = 100)

                _chatMessageState.value = _chatMessageState.value.copy(
                    list = response.data,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _chatMessageState.value = _chatMessageState.value.copy(
                    loading = false,
                    error = "Error fetching messages ${e.message}"
                )
            }
        }
    }

    fun setCurrentThread(chatThread: ChatThread) {
        _currentThread.value = chatThread
    }

    fun setCurrentAssistant(assistantId: String) {
        _currentAssistant.value = assistantId
    }

    fun onEditChatMessageInput(message: String) {
        _chatMessageInputState.value = _chatMessageInputState.value.copy(
            processing = false,
            input = message
        )
    }

    fun clearChatMessageInput() {
        _chatMessageInputState.value = _chatMessageInputState.value.copy(
            input = ""
        )
    }

    fun disableChatMessageInput() {
        _chatMessageInputState.value = _chatMessageInputState.value.copy(
            processing = true
        )
    }

    fun enableChatMessageInput() {
        _chatMessageInputState.value = _chatMessageInputState.value.copy(
            processing = false
        )
    }

    fun sendChatMessage() {
        viewModelScope.launch {
            var running = true
            disableChatMessageInput()

            val message = chatService.createMessage(
                "Bearer <key>",
                _currentThread.value.id,
                ChatMessage(content = _chatMessageInputState.value.input)
            )

            _chatMessageState.value = _chatMessageState.value.copy(
                list = _chatMessageState.value.list.toMutableList().apply { add(0, message) },
                loading = false,
                error = null
            )

            val run = chatService.createRun(
                "Bearer <key>",
                _currentThread.value.id,
                CreateRun(_currentAssistant.value, false)
            )

            while (running) {
                val runUpdate = chatService.retrieveRun(
                        auth = "Bearer <key>",
                        chatThreadId = _currentThread.value.id,
                        run.id
                    )

                if (runUpdate.status == "completed") {
                    running = false
                }

                delay(250)
            }

            val reply = chatService.getMessages(
                auth = "Bearer <key>",
                chatThreadId = _currentThread.value.id,
                limit = 100
            )

            _chatMessageState.value = _chatMessageState.value.copy(
                list = reply.data,
                loading = false,
                error = null
            )

            enableChatMessageInput()
        }
    }

}



data class ChatViewState(
    val loading: Boolean = true,
    val list: List<ChatMessageResponse> = emptyList(),
    val error: String? = null
)

data class ChatMessageState(
    val processing: Boolean = false,
    val input: String = ""
)
