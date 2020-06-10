package responsePojo

import models.Project

class MeResponse {

    data class Response(
        val data: Data
    )

    data class Data(
        val me: Me
    )

    data class Me(
        val name: String?,
        val email: String?,
        val createProject: CreateProject?,
        val projects : Array<Project>?
    )

    data class CreateProject(
        val name: String
    )
}
