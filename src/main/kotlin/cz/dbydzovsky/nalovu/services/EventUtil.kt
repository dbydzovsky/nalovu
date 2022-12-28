package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.Game
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.rest.dto.GameRuntimeDto
import cz.dbydzovsky.nalovu.rest.dto.PayloadType
import cz.dbydzovsky.nalovu.rest.dto.SocketPayload
import cz.dbydzovsky.nalovu.sockjs.messages.GameMessage
import cz.dbydzovsky.nalovu.sockjs.messages.UserAnswered
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service

@Service
class EventUtil(
    private val template: SimpMessagingTemplate,
    private val gameRuntimeComposer: GameRuntimeComposer
) {

    fun updateGame(game: Game) {
        val payload = SocketPayload(
            type=PayloadType.Game,
            game= gameRuntimeComposer.convert(game.id!!)
        )
        template.convertAndSend(game.destination(), payload.toMessage())
    }

    fun sendAnswered(game: Game, user: User) {
        val payload = SocketPayload(
            type = PayloadType.Event,
            event = UserAnswered(
                role = UserRole.Player,
                user = user
            )
        )
        template.convertAndSend(game.destination(), payload.toMessage())
    }


    private fun Game.destination(): String {
        return "/topic/message/${id}"
    }

    private fun SocketPayload.toMessage(): Message<SocketPayload> {
        return GenericMessage(this)
    }
}