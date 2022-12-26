package cz.dbydzovsky.nalovu.sockjs.messages

import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.GameQuestion
import cz.dbydzovsky.nalovu.model.User
import org.springframework.messaging.Message

data class UserAnswered(
    val role: UserRole,
    val user: User,
): GameMessage