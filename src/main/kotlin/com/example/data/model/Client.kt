package com.example.data.model

import io.ktor.websocket.*

data class Client(
    val id: String,
    val sessionId: String,
    val socket: WebSocketSession
)
