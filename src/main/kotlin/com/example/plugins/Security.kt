package com.example.plugins

import com.example.data.model.ServerSession
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.util.*

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<ServerSession>("SESSION")
    }

    intercept(Plugins) {
        if(call.sessions.get<ServerSession>() == null) {
            val id = call.parameters["id"] ?: "1"
            call.sessions.set(ServerSession(id, generateNonce()))
        }
    }
}
