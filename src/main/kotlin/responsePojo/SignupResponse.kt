package responsePojo

class SignupResponse {

    data class Response(
        val data: Data
    )

    data class Data(
        val createUser: CreateUser
    )

    data class CreateUser(
        val token: String
    )
}
