package responsePojo

class MeResponse {

    data class Response(
        val data: Data
    )

    data class Data(
        val me: Me
    )

    data class Me(
        val name: String,
        val email: String
    )
}
