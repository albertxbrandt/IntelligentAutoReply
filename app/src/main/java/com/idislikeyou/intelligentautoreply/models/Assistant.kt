package com.idislikeyou.intelligentautoreply.models

import com.google.gson.annotations.SerializedName

data class Assistant(
    val id: String,
    @SerializedName("object")
    val obj: String,
    @SerializedName("created_at")
    val createdAt: Long,
    val name: String?,
    val description: String?,
    val model: String,
    val instructions: String,
    val tools: List<Any>,
    val file_ids: List<String>,
    val metadata: Map<String, Any>
)
