

class AppPaths  {
    companion object {
        private const val ID_PATH_REGEX = "{id:[0-9]+}"

        const val USER_REGISTRATION = "/registration"
        const val API_GAME = "/api/game"
        const val API_GAME_JOIN = "/api/game/join"
        const val API_USER = "/api/user"
        const val API_GAME_ANSWER = "/api/game/answer"
        const val API_GAME_ID = "$API_GAME/$ID_PATH_REGEX"
        const val API_GAME_DEFINITIONS = "/api/game/definition"
        const val API_GAME_DEFINITION_ID = "$API_GAME_DEFINITIONS/$ID_PATH_REGEX"

    }
}