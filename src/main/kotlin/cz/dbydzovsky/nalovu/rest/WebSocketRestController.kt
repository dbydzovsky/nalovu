package cz.dbydzovsky.nalovu.rest

import cz.dbydzovsky.nalovu.sockjs.messages.GameMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

@RestController
class WebSocketRestController(
    private val template: SimpMessagingTemplate
) {

    @GetMapping("/send")
    fun sendGetMessage(): ResponseEntity<Void?> {
        sendMessage(object : GameMessage {
            override fun toString(): String {
                return "ahooj"
            }
        })
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/send")
    fun sendMessage(@RequestBody textMessageDTO: GameMessage): ResponseEntity<Void?> {
        template.convertAndSend("/topic/message", textMessageDTO)
        return ResponseEntity(HttpStatus.OK)
    }

    @MessageMapping("/sendMessage")
    fun receiveMessage(@Payload textMessageDTO: GameMessage): GameMessage {
        // receive message from client
        println(textMessageDTO)
        return textMessageDTO
    }

    @SendTo("/topic/message")
    fun broadcastMessage(@Payload textMessageDTO: GameMessage): GameMessage {
        return textMessageDTO
    }
}