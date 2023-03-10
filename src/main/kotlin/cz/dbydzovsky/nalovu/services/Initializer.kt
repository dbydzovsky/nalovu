package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.data.UserDto
import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.def.GameDefinition
import cz.dbydzovsky.nalovu.model.def.QuestionDefinition
import cz.dbydzovsky.nalovu.model.def.QuestionType
import cz.dbydzovsky.nalovu.repositories.GameDefinitionRepository
import cz.dbydzovsky.nalovu.repositories.GameRepository
import cz.dbydzovsky.nalovu.repositories.QuestionDefinitionRepository
import cz.dbydzovsky.nalovu.rest.dto.CreateGameDto
import cz.dbydzovsky.nalovu.rest.dto.JoinGameDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Service
class Initializer(
    private val gameService: GameService,
    private val userService: UserService,
    private val gameRepository: GameRepository,
    private val gameDefinitionRepository: GameDefinitionRepository,
    private val questionDefinitionRepository: QuestionDefinitionRepository
) {

    @PostConstruct
    fun init() {
        val user = userService.registerNewUserAccount(UserDto(
            username = "admin",
            password = "pass"
        ))
        val hunter = userService.registerNewUserAccount(UserDto(
            username = "hunter",
            password = "pass"
        ))
        val player = userService.registerNewUserAccount(UserDto(
            username = "player",
            password = "pass"
        ))

        val def: GameDefinition = gameService.createGameDefinition("Demo", listOf())

        val questions: List<QuestionDefinition> = listOf(
            QuestionDefinition(
                type=QuestionType.Answer,
                gameDefinition = def,
                answerDefinitions = listOf(),
                groupDefinition = null,
                name = "Který z klárů..?"
            )
        )
        questionDefinitionRepository.saveAll(questions)
        def.questions = questions
        gameDefinitionRepository.save(def)
        val game = gameService.createGame(user, CreateGameDto(
            name = "my game",
            definition = def.id!!
        ))
        gameService.joinGame(hunter, JoinGameDto(game.id!!, UserRole.Hunter))
        gameService.joinGame(player, JoinGameDto(game.id!!, UserRole.Player))
        val second: GameDefinition = gameService.createGameDefinition("Demo 2", listOf())
    }
}