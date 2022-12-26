package cz.dbydzovsky.nalovu.rest

import AppPaths
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.security.UserProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRestController(
    private val userProvider: UserProvider
) {
    @GetMapping(AppPaths.API_USER)
    fun getUser(): User {
        return userProvider.getActualUser()
    }
}