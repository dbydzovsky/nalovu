package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.model.*
import cz.dbydzovsky.nalovu.rest.dto.GamePhaseType
import cz.dbydzovsky.nalovu.rest.dto.GameRuntimeDto
import cz.dbydzovsky.nalovu.rest.dto.GameUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class GameRuntimeComposer(
    private val gameService: GameService
){

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun convert(gameId: Long): GameRuntimeDto {
        val game = gameService.getOne(gameId)
        return GameRuntimeDto(
            name = game.name,
            admin = game.getAdminAssignment().toGameUser(),
            hunter = game.getHunterAssignment()!!.toGameUser(),
            players = game.getPlayerAssignments().map { it.toGameUser() },
            phase = getPhase(game),
            amount = game.getPlayerAssignments()
                .mapNotNull { it.hunterFight }
                .filter { it.finished }
                .sumOf { it.chosenOffer }
        )
    }

    private fun getPhase(game: Game): GamePhaseType {
        if (game.getPlayerAssignments().any { it.moneyFight == null })
            return GamePhaseType.MoneyFight
        if (game.getPlayerAssignments().any {it.hunterFight == null})
            return GamePhaseType.HunterFight
        return GamePhaseType.PointCollect
    }
}

private fun GameAssignment.toGameUser() :GameUser {
    return GameUser(
        id = user.id!!,
        name = user.name,
        role = role
    )
}