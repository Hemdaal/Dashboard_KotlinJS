package models

import responsePojo.LoginResponse
import responsePojo.MeResponse
import responsePojo.SignupResponse
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
        val tokenResponse = responseJson?.unsafeCast<LoginResponse.Response>()
        val token = tokenResponse?.data?.login?.token
        token?.let {
            saveTokenToLocalStorage(token)
        }
        return token != null
    }

    suspend fun getUser(): User? {
        val responseJson = APIClient.performApi(
            userSchema.getUserSchema()
        )
        val userResponse = responseJson?.unsafeCast<MeResponse.Response>()

        return userResponse?.data?.me?.let {
            User(it.name, it.email)
        }
    }

    suspend fun createUser(name: String, email: String, password: String): Boolean {
        val responseJson = APIClient.performApi(
            schema = userSchema.getCreateUserSchema(name, email, password),
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

    fun getToken() = localStorage.getItem(tokenKeyStr)
}