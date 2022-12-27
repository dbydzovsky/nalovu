package cz.dbydzovsky.nalovu.rest

import cz.dbydzovsky.nalovu.data.UserDto
import cz.dbydzovsky.nalovu.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class LoginRestController(
    val passwordEncoder: BCryptPasswordEncoder,
    val userService: UserService,
    val authenticationManager: AuthenticationManager
) {

    @PostMapping("/signin")
    fun registerUserAccount(
        @RequestBody userDto: UserDto,
        request: HttpServletRequest,
    ) {
        val authToken = UsernamePasswordAuthenticationToken(userDto.username, userDto.password)
        authToken.details = WebAuthenticationDetails(request)
        val authentication: Authentication = authenticationManager.authenticate(authToken)
        SecurityContextHolder.getContext().authentication = authentication
    }
}