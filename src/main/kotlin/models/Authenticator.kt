package models

import responsePojo.TokenResponse
import wrappers.GraphQLClient
import kotlin.browser.localStorage


class Authenticator {

    private val loginSchema = """
        {
          login(email: {email}, password:{password}) {
            token
          }
        }
    """.trimIndent()

    private val signupSchema = """
        {
          createUser(name:"{name}",email:"{email}", password:"{password}") {
            token
          }
        }
    """.trimIndent()

    private val tokenKeyStr = "token_key"

    suspend fun getUser(email: String, password: String): Boolean {
        val responseJson = GraphQLClient.performApi(
            loginSchema,
            mutableMapOf(
                "email" to email,
                "password" to password
            )
        )
        val tokenResponse = responseJson?.unsafeCast<TokenResponse>()

        return tokenResponse?.token != null
    }

    suspend fun createUser(name: String, email: String, password: String): Boolean {
        val responseJson = GraphQLClient.performApi(
            signupSchema,
            mutableMapOf(
                "name" to name,
                "email" to email,
                "password" to password
            )
        )
        val tokenResponse = responseJson?.unsafeCast<TokenResponse>()

        return tokenResponse?.token != null
    }

    private fun saveTokenToLocalStorage(token: String) {
        localStorage.setItem(tokenKeyStr, token)
    }

    fun getToken() = localStorage.getItem(tokenKeyStr)
}