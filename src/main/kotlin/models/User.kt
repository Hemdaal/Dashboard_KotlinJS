package models

import responsePojo.MeResponse
import wrappers.APIClient
import wrappers.ProjectSchema

class User(
    val name: String,
    val email: String
) {

    suspend fun createProject(projectName: String): Project? {
        val responseJson = APIClient.performApi(
            schema = ProjectSchema().getCreateProjectSchema(projectName)
        )
        val meResponse = responseJson?.unsafeCast<MeResponse.Response>()
        return meResponse?.data?.me?.createProject?.let {
            Project(it.name)
        }
    }

    suspend fun getProjects(): List<Project> {
        val responseJson = APIClient.performApi(
            schema = ProjectSchema().getProjectsSchema()
        )
        val meResponse = responseJson?.unsafeCast<MeResponse.Response>()
        return meResponse?.data?.me?.projects?.map {
            Project(it.name)
        } ?: emptyList()
    }
}