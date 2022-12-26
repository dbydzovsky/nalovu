package cz.dbydzovsky.nalovu.rest.dto

import cz.dbydzovsky.nalovu.data.UserRole

data class CreateGameDto(
    val name: String,
    val definition: Long,
)

data class JoinGameDto(
    val gameId: Long,
    val role: UserRole
)