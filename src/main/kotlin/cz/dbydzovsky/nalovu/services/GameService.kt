package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.repositories.GameRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class GameService(
    val gameRepository: GameRepository
) {

    fun findAll(): List<Game>{
        return gameRepository.findAll()
    }
}