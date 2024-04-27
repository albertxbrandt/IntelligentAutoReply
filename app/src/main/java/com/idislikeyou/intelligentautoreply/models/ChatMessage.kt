package com.idislikeyou.intelligentautoreply.models

import com.google.gson.annotations.SerializedName

data class ChatMessage(
    val role: String = "user",
    val content: String
)

data class ChatMessageList(
    val `object`: String,
    val data: List<ChatMessageResponse>
)

data class ChatMessageResponse(
    val id: String,
    @SerializedName("object")
    val obj: String,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("thread_id")
    val threadId: String,
    val role: String,
    val content: List<Content>,
    @SerializedName("file_ids")
    val fileIds: List<String>,
    @SerializedName("assistant_id")
    val assistantId: String?,
    @SerializedName("run_id")
    val runId: String?,
    val metadata: Map<String, Any>

) {
    data class Content (
        val type: String,
        val text: Text
    ) {
        data class Text (
            val value: String,
            val annotations: List<String>
        )
    }
}

