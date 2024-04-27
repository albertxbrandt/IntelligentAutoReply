package com.idislikeyou.intelligentautoreply.util

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val selectedApiKey: String = "",
    val apiKeys: PersistentList<String> = persistentListOf(),
    val selectedAssistantId: String = "",
    val selectedChatThreadId: String = ""
)




