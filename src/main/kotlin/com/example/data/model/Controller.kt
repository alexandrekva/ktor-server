package com.example.data.model

import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

class Controller {
    private val clients = ConcurrentHashMap<String, Client>()

    fun onJoin(
        id: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        if (!clients.containsKey(id)) {
            clients[id] = Client(
                id = id,
                sessionId = sessionId,
                socket = socket
            )
        }

        println("Conectado id: $id")
    }

    suspend fun sendMessage(receiverId: String, message: String) {
        val client = clients[receiverId]
        client?.socket?.send(message)
    }
}