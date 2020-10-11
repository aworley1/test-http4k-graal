package io.github.aworley1

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.format.KotlinxSerialization.json
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.time.Instant

fun main() {
    val jsonLens = Body.json().toLens()

    val app = { request: Request ->
        val requestModel =
            Json.decodeFromJsonElement<RequestModel>(jsonLens(request))

        Response(OK)
            .body(
                Json.encodeToString(
                    ResponseModel(
                        message = "Hello ${requestModel.name}",
                        timestamp = Instant.now().toString()
                    )
                )
            )
    }

    app.asServer(Jetty(9000)).start()
}

@Serializable
data class RequestModel(val name: String)

@Serializable
data class ResponseModel(val message: String, val timestamp: String)