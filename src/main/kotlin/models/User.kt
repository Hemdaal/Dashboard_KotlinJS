package models

class User(
    val name: String,
    val email: String
) {

    suspend fun createProject() : Project? {
        return null
    }

    fun getProjects(): List<Project> {
        return emptyList()
    }
}