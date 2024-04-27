package com.idislikeyou.intelligentautoreply.models

import com.google.gson.annotations.SerializedName

data class ChatThread(
    val id: String,
    @SerializedName("object")
    val obj: String = "",
    @SerializedName("created_at")
    val createdAt: Long = 0L,
    val metadata: Map<String, Any> = emptyMap()
)