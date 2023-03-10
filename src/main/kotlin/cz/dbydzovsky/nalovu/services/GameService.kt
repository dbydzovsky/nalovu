package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.*
import cz.dbydzovsky.nalovu.model.def.GameDefinition
import cz.dbydzovsky.nalovu.model.def.QuestionDefinition
import cz.dbydzovsky.nalovu.repositories.*
import cz.dbydzovsky.nalovu.rest.dto.CorrectAnswerDto
import cz.dbydzovsky.nalovu.rest.dto.CreateGameDto
import cz.dbydzovsky.nalovu.rest.dto.JoinGameDto
import cz.dbydzovsky.nalovu.rest.dto.StartMoneyFightDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class GameService(
    val gameRepository: GameRepository,
    val gameDefinitionRepository: GameDefinitionRepository,
    val gameQuestionRepository: GameQuestionRepository,
    val gameAssignmentRepository: GameAssignmentRepository,
    val moneyFightRepository: MoneyFightRepository,
    val questionDefinitionRepository: QuestionDefinitionRepository
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
    fun createGameDefinition(name: String,
                             questions: List<QuestionDefinition>)
    : GameDefinition {
        val questions = questionDefinitionRepository.saveAll(questions)
        return gameDefinitionRepository.save(GameDefinition(
            id=null,
            name=name,
            questions = questions
        ))
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

    @Transactional
    fun joinGame(user: User, dto: JoinGameDto): Game {
        val game = getOne(dto.gameId)
        when(dto.role) {
            UserRole.Hunter -> if (game.hasHunter)
                throw IllegalStateException("Hunter is already assigned")
            UserRole.Player -> if (game.getPlayerAssignments().size > MAX_PLAYERS)
                throw IllegalStateException("Too much players")
            UserRole.Admin -> throw IllegalStateException("Admin already assigned")
        }
        if (game.assignments.any { it.user.id == user.id } ) {
            throw IllegalStateException("User already assigned")
        }
        val assignment = gameAssignmentRepository.save(GameAssignment(
            game= game,
            user = user,
            role = dto.role
        ))
        game.assignments.add(assignment)
        return gameRepository.save(game)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun startMoneyFight(gameId: Long, dto: StartMoneyFightDto) {
        val game = getOne(gameId)
        val assignment = game.getPlayerAssignments()
            .single { it.user.id === dto.userId }
        assert(assignment.moneyFight == null)
        assignment.moneyFight = moneyFightRepository.save(MoneyFight(
            correctAnswers = 0,
            amount = 0
        ))
        gameAssignmentRepository.save(assignment)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun moneyFightAnswered(gameId: Long, dto: CorrectAnswerDto) {
        val game = getOne(gameId)
        val assignment = game.getPlayerAssignments()
            .single { it.user.id === dto.userId }
        val moneyFight = assignment.moneyFight!!
        moneyFight.correctAnswers+= 1
        moneyFight.amount+= 5000
        moneyFightRepository.save(moneyFight)
    }
}