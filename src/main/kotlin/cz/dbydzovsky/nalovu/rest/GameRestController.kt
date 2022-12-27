package cz.dbydzovsky.nalovu.rest

import AppPaths
import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.model.def.GameDefinition
import cz.dbydzovsky.nalovu.rest.dto.CreateGameDto
import cz.dbydzovsky.nalovu.rest.dto.JoinGameDto
import cz.dbydzovsky.nalovu.security.UserProvider
import cz.dbydzovsky.nalovu.services.GameService
import org.apache.coyote.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest
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

    @PostMapping(AppPaths.API_GAME_SET)
    fun setGame(@RequestBody dto: JoinGameDto, request: HttpServletRequest) :ResponseEntity<Game> {
        val user = userProvider.getActualUser()
        val game = gameService.getOne(dto.gameId)
        if (!game.assignments.any { it.user.id === user.id }) {
            throw IllegalStateException("User is not part of this game")
        }
        val springCookie = ResponseCookie.from("gameid", game.id.toString())
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(60 * 60 * 24)
            .build()
        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, springCookie.toString())
            .body(game);
    }

    @PostMapping(AppPaths.API_GAME_JOIN)
    fun joinNewGame(@RequestBody dto: JoinGameDto, request: HttpServletRequest): ResponseEntity<Game> {
        val user = userProvider.getActualUser()
        val game = gameService.joinGame(user, dto)
        val springCookie = ResponseCookie.from("gameid", game.id.toString())
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(60 * 60 * 24)
            .build()
        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, springCookie.toString())
            .body(game);
    }
}