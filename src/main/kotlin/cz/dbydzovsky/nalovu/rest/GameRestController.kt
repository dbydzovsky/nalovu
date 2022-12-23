package cz.dbydzovsky.nalovu.rest

import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.services.GameService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping
@RestController
class GameRestController(
    val gameService: GameService
) {

    @GetMapping(AppPaths.API_GAME)
    fun getAllGames(): List<Game> {
        return gameService.findAll()
    }

    @PostMapping(AppPaths.API_GAME)
    fun createNewGame() {

    }
}