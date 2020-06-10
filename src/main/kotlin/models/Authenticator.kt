package models

import responsePojo.LoginResponse
import responsePojo.MeResponse
import responsePojo.SignupResponse
import wrappers.APIClient
import wrappers.UserSchema
import kotlin.browser.localStorage

class Authenticator {

    private val tokenKeyStr = "token_key"

    suspend fun login(email: String, password: String): Boolean {
        val responseJson = APIClient.performApi(
            schema = UserSchema().getLoginSchema(email, password),
            authorized = false
        )
        val tokenResponse = responseJson?.unsafeCast<LoginResponse.Response>()
        val token = tokenResponse?.data?.login?.token
        token?.let {
            saveTokenToLocalStorage(token)
        }
        return token != null
    }

    suspend fun getUser(): User? {

        val responseJson = APIClient.performApi(
            UserSchema().getUserSchema()
        )
        val userResponse = responseJson?.unsafeCast<MeResponse.Response>()

        return userResponse?.data?.me?.let {
            User(it.name, it.email)
        }
    }

    suspend fun createUser(name: String, email: String, password: String): Boolean {
        val responseJson = APIClient.performApi(
            schema = UserSchema().getCreateUserSchema(name, email, password),
            authorized = false
        )
        val tokenResponse = responseJson?.unsafeCast<SignupResponse.Response>()
        val token = tokenResponse?.data?.createUser?.token
        saveTokenToLocalStorage(token ?: "")
        return token != null
    }

    private fun saveTokenToLocalStorage(token: String) {
        localStorage.setItem(tokenKeyStr, token)
    }

    fun logout() {
        localStorage.setItem(tokenKeyStr, "")
    }

    fun getToken(): String? {
        val token = localStorage.getItem(tokenKeyStr)
        return if (token.isNullOrEmpty()) null else token
    }
}