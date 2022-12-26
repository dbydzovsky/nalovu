package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.model.GameAssignment
import cz.dbydzovsky.nalovu.model.GameQuestion
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.model.def.GameDefinition
import cz.dbydzovsky.nalovu.repositories.GameAssignmentRepository
import cz.dbydzovsky.nalovu.repositories.GameDefinitionRepository
import cz.dbydzovsky.nalovu.repositories.GameQuestionRepository
import cz.dbydzovsky.nalovu.repositories.GameRepository
import cz.dbydzovsky.nalovu.rest.dto.CreateGameDto
import cz.dbydzovsky.nalovu.rest.dto.JoinGameDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    val gameRepository: GameRepository,
    val gameDefinitionRepository: GameDefinitionRepository,
    val gameQuestionRepository: GameQuestionRepository,
    val gameAssignmentRepository: GameAssignmentRepository
) {

    companion object{
        const val MAX_PLAYERS = 4
    }
    fun findAll(): List<Game>{
        return gameRepository.findAll()
    }

    fun findAllDefinitions(): List<GameDefinition> {
        return gameDefinitionRepository.findAll()
    }

    fun getOne(id: Long) : Game {
        return gameRepository.findById(id).get()
    }

    fun getDefinition(id: Long) :GameDefinition {
        return gameDefinitionRepository.findById(id).get()
    }

    @Transactional
    fun createGame(user: User, dto: CreateGameDto): Game {
        val newGame = gameRepository.save(Game(
            name=dto.name
        ))
        val gameQuestions = getDefinition(dto.definition).questions.map { questionDef ->
            GameQuestion(
                question = questionDef,
                game = newGame,
                used = false,
                user = null
            )
        }
        newGame.questions = gameQuestionRepository.saveAll(gameQuestions)
        val assignment = gameAssignmentRepository.save(GameAssignment(
            game=  newGame,
            user = user,
            role = UserRole.Admin
        ))
        newGame.assignments.add(assignment)
        return gameRepository.save(newGame)
    }

    fun joinGame(user: User, dto: JoinGameDto): Game {
        val game = getOne(dto.gameId)
        when(dto.role) {
            UserRole.Hunter -> if (game.hasHunter)
                throw IllegalStateException("Hunter is already assigned")
            UserRole.Player -> if (game.playerAssignments.size > MAX_PLAYERS)
                throw IllegalStateException("Too much players")
            UserRole.Admin -> throw IllegalStateException("Admin already assigned")
        }
        val assignment = gameAssignmentRepository.save(GameAssignment(
            game= game,
            user = user,
            role = UserRole.Admin
        ))
        game.assignments.add(assignment)
        return gameRepository.save(game)
    }
}