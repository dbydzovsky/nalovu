package cz.dbydzovsky.nalovu.rest

import AppPaths
import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.model.def.GameDefinition
import cz.dbydzovsky.nalovu.rest.dto.CreateGameDto
import cz.dbydzovsky.nalovu.rest.dto.JoinGameDto
import cz.dbydzovsky.nalovu.security.UserProvider
import cz.dbydzovsky.nalovu.services.GameService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RequestMapping
@RestController
class GameRestController(
    val gameService: GameService,
    val userProvider: UserProvider
) {

    @GetMapping(AppPaths.API_GAME)
    fun getAllGames(): List<Game> {
        return gameService.findAll()
    }

    @GetMapping(AppPaths.API_GAME_ID)
    fun getGame(@PathParam("id") id: Long): Game {
        return gameService.getOne(id)
    }

    @GetMapping(AppPaths.API_GAME_DEFINITION_ID)
    fun getGameDefinition(@PathParam("id") id: Long): GameDefinition {
        return gameService.getDefinition(id)
    }

    @GetMapping(AppPaths.API_GAME_DEFINITIONS)
    fun getGameDefinitions() : List<GameDefinition> {
        return gameService.findAllDefinitions()
    }

    @PostMapping(AppPaths.API_GAME)
    fun createNewGame(@RequestBody dto: CreateGameDto): Game {
        val user = userProvider.getActualUser()
        return gameService.createGame(user, dto)
    }

    @PostMapping(AppPaths.API_GAME_JOIN)
    fun joinNewGame(@RequestBody dto: JoinGameDto): Game {
        val user = userProvider.getActualUser()
        return gameService.joinGame(user, dto)
    }
}