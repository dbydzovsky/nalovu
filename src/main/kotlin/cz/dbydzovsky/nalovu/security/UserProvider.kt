package cz.dbydzovsky.nalovu.security

import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.services.UserService
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserProvider(
    private val userService: UserService
) {

    fun getActualUser(): User {
        val auth = SecurityContextHolder.getContext().authentication
        return userService.getUser(auth.name)
            ?: throw AuthenticationServiceException("Not authenticated")
    }
}