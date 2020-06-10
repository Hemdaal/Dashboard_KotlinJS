package wrappers

class ProjectSchema : GraphQLSchema() {

    private val createProjectQuery = """
        query createProject(${dVar}name: String!) {
            me {
                createProject(name : ${dVar}name) {
                    name
                }
            }
        }
    """.trimIndent()

    private val projectsQuery = """
        query getProjects {
            me {
                projects {
                    name
                }
            }
        }
    """.trimIndent()

    fun getCreateProjectSchema(name: String): String {
        return getSchema(
            operation = "createProject",
            query = createProjectQuery,
            vars = mapOf(
                "name" to name
            )
        )
    }

    fun getProjectsSchema(): String {
        return getSchema(
            operation = "getProjects",
            query = projectsQuery
        )
    }
}