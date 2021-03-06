package wrappers.graphql

class UserSchema : GraphQLSchema() {

    private val loginQuery = """
        query Login(${dVar}email: String!, ${dVar}password: String!) {
            login(email: ${dVar}email, password: ${dVar}password) {
                token
            }
        }
    """.trimIndent()

    private val createUserQuery = """
        query CreateUser(${dVar}name: String!, ${dVar}email: String!, ${dVar}password: String!) {
            createUser(name: ${dVar}name, email: ${dVar}email, password: ${dVar}password) {
                token
            }
        }
    """.trimIndent()

    private val userQuery = """
        query me {
            me {
                name,
                email
            }
        }
    """.trimIndent()

    fun getLoginSchema(email: String, password: String): String {
        return getSchema(
            operation = "Login",
            query = loginQuery,
            vars = mapOf(
                "email" to email,
                "password" to password
            )
        )
    }

    fun getCreateUserSchema(name: String, email: String, password: String): String {
        return getSchema(
            operation = "CreateUser",
            query = createUserQuery,
            vars = mapOf(
                "name" to name,
                "email" to email,
                "password" to password
            )
        )
    }

    fun getUserSchema() = getSchema("me", userQuery)
}