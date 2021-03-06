package wrappers

import kotlinx.coroutines.await
import models.Authenticator
import org.w3c.fetch.*
import kotlin.browser.window
import kotlin.js.Json

object APIClient {

    private const val GRAPHQL_ENDPOINT = "http://localhost:5555/graphql"

    suspend fun performApi(
        schema: String,
        authorized: Boolean = true
    ): Any? {

        try {

            val headers = Headers().apply {
                append("accept", "*/*")
                append("Content-Type", "application/json")
                Authenticator().getToken()?.let { token ->
                    if (authorized) {
                        append("Authorization", "Bearer $token")
                    }
                }
            }

            val result = window.fetch(
                GRAPHQL_ENDPOINT,
                RequestInit(
                    method = "POST",
                    headers = headers,
                    body = schema,
                    mode = RequestMode.Companion.CORS
                )
            ).await()
            if (result.ok) {
                return result.json().await()
            }
        } catch (e: Exception) {

        }

        return null
    }
}