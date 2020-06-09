package wrappers

class UserSchema : GraphQLSchema() {

    private val loginQuery = """
        query Login(${DVAR}email: String!, ${DVAR}password: String!) {
            login(email: ${DVAR}email, password: ${DVAR}password) {
                token
            }
        }
    """.trimIndent()

    private val createUserQuery = """
        query CreateUser(${DVAR}name: String!, ${DVAR}email: String!, ${DVAR}password: String!) {
            createUser(name: ${DVAR}name, email: ${DVAR}email, password: ${DVAR}password) {
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