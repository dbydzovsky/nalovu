package cz.dbydzovsky.nalovu.rest

import AppPaths
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.model.getPlayerAssignments
import cz.dbydzovsky.nalovu.rest.dto.CorrectAnswerDto
import cz.dbydzovsky.nalovu.rest.dto.StartMoneyFightDto
import cz.dbydzovsky.nalovu.security.UserProvider
import cz.dbydzovsky.nalovu.services.EventUtil
import cz.dbydzovsky.nalovu.services.GameService
import cz.dbydzovsky.nalovu.services.RuntimeService
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping
@RestController
class RuntimeRestController(
    private val userProvider: UserProvider,
    private val gameService: GameService,
    private val runtimeService: RuntimeService,
    private val eventUtil: EventUtil
) {

    @PostMapping(AppPaths.API_RUNTIME_MONEY_FIGHT_AVAILABLE)
    fun getAvailables(@CookieValue(value = "gameid") gameId: Long): List<User> {
        val game = gameService.getOne(gameId)
        return game.getPlayerAssignments().filter {
            it.moneyFight == null
        }.map {
            it.user
        }
    }

    @PostMapping(AppPaths.API_RUNTIME_MONEY_FIGHT_CORRECT_ASNWER)
    fun correctlyAnsweredMoneyFight(
        @CookieValue(value = "gameid") gameId: Long,
        @RequestBody dto: CorrectAnswerDto) {
        gameService.moneyFightAnswered(gameId, dto)
    }

    @PostMapping(AppPaths.API_RUNTIME_MONEY_FIGHT_CORRECT_ASNWER)
    fun startMoneyFight(
        @CookieValue(value = "gameid") gameId: Long,
        @RequestBody dto: StartMoneyFightDto) {
        gameService.startMoneyFight(gameId, dto)
    }

    @PostMapping(AppPaths.API_RUNTIME_UPDATE)
    fun updateGame(@CookieValue(value = "gameid") gameId: Long) {
        val game = gameService.getOne(gameId)
        eventUtil.updateGame(game)
    }

//    https://reflectoring.io/spring-boot-cookies/
    @PostMapping(AppPaths.API_RUNTIME_ANSWER)
    fun answer(
        @CookieValue(value = "gameid") gameId: Long,
        @RequestBody dto: CorrectAnswerDto) {
        val user = userProvider.getActualUser()
        val game = gameService.getOne(gameId)
//        runtimeService.answer(gameId, dto)
        eventUtil.sendAnswered(game, user)
        eventUtil.updateGame(game)
    }
}