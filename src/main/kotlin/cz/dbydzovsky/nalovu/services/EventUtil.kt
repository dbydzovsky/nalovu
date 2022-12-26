package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.sockjs.messages.GameMessage
import cz.dbydzovsky.nalovu.sockjs.messages.UserAnswered
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service

@Service
class EventUtil(
    private val template: SimpMessagingTemplate
) {

    fun sendAnswered(game: Game, user: User) {
        template.send(game.destination(), UserAnswered(
            role = UserRole.Player,
            user = user
        ).toMessage())
    }


    private fun Game.destination(): String {
        return "/topic/message/${id}"
    }

    private fun GameMessage.toMessage(): Message<GameMessage> {
        return GenericMessage(this)
    }
}