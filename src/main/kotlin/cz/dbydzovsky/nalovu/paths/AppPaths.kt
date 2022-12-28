

class AppPaths  {
    companion object {
        private const val ID_PATH_REGEX = "{id:[0-9]+}"

        const val API_REGISTRATION = "/api/registration"
        const val API_LOGIN = "/api/signin"
        const val API_LOGOUT = "/api/logout"
        const val API_GAME = "/api/game"
        const val API_GAME_JOIN = "/api/game/join"
        const val API_GAME_SET = "/api/game/set"
        const val API_USER = "/api/user"

        const val API_RUNTIME_MONEY_FIGHT_START = "/api/runtime/moneyfight/start"
        const val API_RUNTIME_MONEY_FIGHT_AVAILABLE = "/api/runtime/moneyfight/available"
        const val API_RUNTIME_MONEY_FIGHT_CORRECT_ASNWER = "/api/runtime/moneyfight/correct"

        const val API_RUNTIME_UPDATE = "/api/runtime/update"
        const val API_RUNTIME_ANSWER = "/api/runtime/answer"
        const val API_GAME_ID = "$API_GAME/$ID_PATH_REGEX"
        const val API_GAME_DEFINITIONS = "/api/game/definition"
        const val API_GAME_DEFINITION_ID = "$API_GAME_DEFINITIONS/$ID_PATH_REGEX"

    }
}