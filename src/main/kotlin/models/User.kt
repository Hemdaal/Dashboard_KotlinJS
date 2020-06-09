package models

class User(
    val name: String,
    val email: String
) {
    fun getProjects(): List<Project> {
        TODO("Not yet implemented")
    }
}