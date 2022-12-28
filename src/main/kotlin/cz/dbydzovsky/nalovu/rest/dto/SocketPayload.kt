package cz.dbydzovsky.nalovu.rest.dto

enum class PayloadType {
    Game, Event
}

data class SocketPayload (
    val type: PayloadType,
    val game: GameRuntimeDto? = null,
    val event: GameEventDto? = null
)