package com.idislikeyou.intelligentautoreply.models

data class CreateAssistant(
    val model: String,
    val name: String,
    val description: String,
    val instructions: String
)
