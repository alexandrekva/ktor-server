package com.example.data.model

import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach

fun Route.serverSocket(controller: Controller) {
    webSocket("/server-socket") {
        val session = call.sessions.get<ServerSession>()
        if (session == null) {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Sem sessão"))
            return@webSocket
        }

        controller.onJoin(
            id = session.id,
            sessionId = session.sessionId,
            socket = this
        )

        incoming.consumeEach { frame ->
            if (frame is Frame.Text) {
                val msg = frame.readText()
                val splitted = msg.split("_")

                try {
                    val id = splitted[2]
                    controller.sendMessage(id, msg)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }
}