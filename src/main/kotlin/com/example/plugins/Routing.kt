package com.example.plugins

import com.example.data.model.Controller
import com.example.data.model.serverSocket
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    val controller = Controller()
    install(Routing) {
        serverSocket(controller)
    }
}
