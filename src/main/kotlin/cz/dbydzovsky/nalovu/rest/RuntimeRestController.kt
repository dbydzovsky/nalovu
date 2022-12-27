package cz.dbydzovsky.nalovu.rest

import AppPaths
import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.rest.dto.AnswerDto
import cz.dbydzovsky.nalovu.security.UserProvider
import cz.dbydzovsky.nalovu.services.EventUtil
import cz.dbydzovsky.nalovu.services.RuntimeService
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam


@RequestMapping
@RestController
class RuntimeRestController(
    private val userProvider: UserProvider,
    private val runtimeService: RuntimeService,
    private val eventUtil: EventUtil
) {

//    https://reflectoring.io/spring-boot-cookies/
    @PostMapping(AppPaths.API_GAME_ANSWER)
    fun answer(
        @CookieValue(value = "gameid") gameId: Long,
        @RequestBody dto: AnswerDto) {
        val user = userProvider.getActualUser()
//        runtimeService.answer(gameId, dto)
        eventUtil.sendAnswered(Game(name="x"), user)
    }
}