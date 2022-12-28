package cz.dbydzovsky.nalovu.rest.dto

enum class EventType {
    Answered
}

abstract class GameEventDto(
    val type: EventType
)