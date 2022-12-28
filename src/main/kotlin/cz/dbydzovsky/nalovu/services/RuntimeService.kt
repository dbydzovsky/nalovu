package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.rest.dto.CorrectAnswerDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RuntimeService(
    private val gameService: GameService,
) {

    @Transactional
    fun answer(gameId: Long, answer: CorrectAnswerDto): Game {
        return gameService.getOne(gameId)
    }
}