package responsePojo

class LoginResponse {

    data class Response(
        val data: Data
    )

    data class Data(
        val login: Login
    )

    data class Login(
        val token: String
    )
}
