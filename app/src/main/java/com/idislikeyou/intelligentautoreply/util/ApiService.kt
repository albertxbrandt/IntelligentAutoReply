package com.idislikeyou.intelligentautoreply.util

import com.google.gson.annotations.SerializedName
import com.idislikeyou.intelligentautoreply.models.Assistant
import com.idislikeyou.intelligentautoreply.models.ChatMessage
import com.idislikeyou.intelligentautoreply.models.ChatMessageList
import com.idislikeyou.intelligentautoreply.models.ChatMessageResponse
import com.idislikeyou.intelligentautoreply.models.ChatThread
import com.idislikeyou.intelligentautoreply.models.CreateAssistant
import com.idislikeyou.intelligentautoreply.models.CreateRun
import com.idislikeyou.intelligentautoreply.models.RunObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://api.openai.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val chatService = retrofit.create(ApiService::class.java)

interface ApiService {

    @POST("assistants")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun createAssistant(
        @Header("Authorization") auth: String,
        @Body createAssistant: CreateAssistant
    ): Assistant


    @GET("models")
    suspend fun validateKey(
        @Header("Authorization") auth: String,
    ): validateResponse<Any>

    // Fetch a list of assistants for an api key
    @GET("assistants")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun fetchAssistants(
        @Header("Authorization") auth: String,
    ): validateResponse<Assistant>

    @POST("threads")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun createChatThread(
        @Header("Authorization") auth: String
    ): ChatThread

    @GET("threads/{thread_id}")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun retrieveChatThread(
        @Header("Authorization") auth: String,
        @Path("thread_id") chatThreadId: String

    ): ChatThread

    @POST("threads/{thread_id}/messages")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun createMessage(
        @Header("Authorization") auth: String,
        @Path("thread_id") chatThreadId: String,
        @Body chatMessage: ChatMessage
    ): ChatMessageResponse


    @GET("threads/{thread_id}/messages")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun getMessages(
        @Header("Authorization") auth: String,
        @Path("thread_id") chatThreadId: String,
        @Query("limit") limit: Int = 20
    ): ChatMessageList

    @POST("threads/{thread_id}/runs")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun createRun(
        @Header("Authorization") auth: String,
        @Path("thread_id") chatThreadId: String,
        @Body createRun: CreateRun
    ): RunObject

    @GET("threads/{thread_id}/runs/{run_id}")
    @Headers(
        "Content-Type: application/json",
        "OpenAI-Beta: assistants=v1"
    )
    suspend fun retrieveRun(
        @Header("Authorization") auth: String,
        @Path("thread_id") chatThreadId: String,
        @Path("run_id") runId: String,
    ): RunObject

}

data class validateResponse<T>(
    @SerializedName("object")
    val obj: String,
    val data: List<T>
)