package wrappers

import kotlinx.coroutines.await
import models.Authenticator
import org.w3c.fetch.RequestInit
import kotlin.browser.window

object GraphQLClient {

    private const val GRAPHQL_ENDPOINT = "http://localhost:8080/graphql"

    suspend fun performApi(schema: String, schemaVars: Map<String, String>): Any? {
        schemaVars.forEach {
            schema.replace("$" + it.key, it.value)
        }

        val headers = mutableMapOf<String, String>().apply {
            put("Content-Type", "application/json")
            Authenticator().getToken()?.let { token ->
                put("Authorization", "Bearer $token")
            }
        }

        return window.fetch(
            GRAPHQL_ENDPOINT, RequestInit(
                method = "POST",
                headers = headers,
                body = schema
            )
        ).await().json().await()
    }
}