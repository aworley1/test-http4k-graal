package io.github.aworley1

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    val app = { request: Request -> Response(OK).body("Hello, ${request.query("name")}!") }

    app.asServer(Jetty(9000)).start()
}