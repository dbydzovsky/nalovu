package cz.dbydzovsky.nalovu.rest.dto

import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.User

data class GameUser(
    val id: Long,
    val name: String,
    val role: UserRole
)

enum class GamePhaseType {
    MoneyFight,
    HunterFight,
    PointCollect,
    HunterCollect
}

data class GameRuntimeDto(
    val name: String,
    val admin: GameUser,
    val hunter: GameUser,
    val players: List<GameUser>,
    val phase: GamePhaseType,
    val amount: Long
)