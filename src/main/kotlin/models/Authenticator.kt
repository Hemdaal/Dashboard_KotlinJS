package models

import responsePojo.TokenResponse
import responsePojo.UserResponse
import wrappers.APIClient
import wrappers.UserSchema
import kotlin.browser.localStorage


class Authenticator {

    private val tokenKeyStr = "token_key"
    private val userSchema = UserSchema()

    suspend fun login(email: String, password: String): Boolean {
        val responseJson = APIClient.performApi(
            schema = userSchema.getLoginSchema(email, password),
            authorized = false
        )
        val tokenResponse = responseJson?.unsafeCast<TokenResponse>()
        saveTokenToLocalStorage(tokenResponse?.token ?: "")
        return tokenResponse?.token != null
    }

    suspend fun getUser(): User? {
        val responseJson = APIClient.performApi(
            userSchema.getUserSchema()
        )
        val userResponse = responseJson?.unsafeCast<UserResponse>()

        return userResponse?.let {
            User(it.name, it.email)
        }
    }

    suspend fun createUser(name: String, email: String, password: String): Boolean {
        val responseJson = APIClient.performApi(
            schema = userSchema.getCreateUserSchema(name, email, password),
            authorized = false
        )
        val tokenResponse = responseJson?.unsafeCast<TokenResponse>()
        saveTokenToLocalStorage(tokenResponse?.token ?: "")
        return tokenResponse?.token != null
    }

    private fun saveTokenToLocalStorage(token: String) {
        localStorage.setItem(tokenKeyStr, token)
    }

    fun getToken() = localStorage.getItem(tokenKeyStr)
}