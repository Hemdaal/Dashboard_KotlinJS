package wrappers.graphql

import kotlin.js.Json

abstract class GraphQLSchema {

    protected val dVar = "$"

    fun getSchema(
        operation: String,
        query: String,
        vars: Map<String, Any?> = emptyMap()
    ): String {
        val jsonMap = mutableMapOf<String, Any?>()
        jsonMap["operationName"] = operation
        jsonMap["query"] = query
        jsonMap["variables"] = vars

        return JSON.stringify(json(jsonMap))
    }

    private fun json(map: Map<*, *>): Json {
        val res: dynamic = js("({})")
        for ((name, value) in map) {
            if (value is Map<*, *>) {
                res[name] = json(value)
            } else {
                res[name] = value
            }
        }
        return res
    }
}