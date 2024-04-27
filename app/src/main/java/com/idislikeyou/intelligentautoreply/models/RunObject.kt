package com.idislikeyou.intelligentautoreply.models

import com.google.gson.annotations.SerializedName

data class CreateRun(
    @SerializedName("assistant_id")
    val assistantId: String,
    val stream: Boolean
)

data class RunObject(
    val id: String,
    @SerializedName("object")
    val obj: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("assistant_id")
    val assistantId: String,
    @SerializedName("thread_id")
    val threadId: String,
    val status: String,
    @SerializedName("started_at")
    val startedAt: Long?,
    @SerializedName("expires_at")
    val expiresAt: Long?,
    @SerializedName("cancelled_at")
    val cancelledAt: Long?,
    @SerializedName("failed_at")
    val failedAt: Long?,
    @SerializedName("completed_at")
    val completedAt: Long?,
    @SerializedName("last_error")
    val lastError: LastError?,
    val model: String,
    val instructions: String?,
    val tools: List<Any>, // TODO
    @SerializedName("file_ids")
    val fileIds: List<Any>, // TODO
    val metadata: Map<String, Any>,
    val usage: Usage?
) {
    data class LastError (
        val code: String,
        val message: String
    )

    data class Usage (
        @SerializedName("completion_tokens")
        val completionTokens: Int,
        @SerializedName("prompt_tokens")
        val promptTokens: Int,
        @SerializedName("total_tokens")
        val totalTokens: Int,
    )
}