package cz.dbydzovsky.nalovu.rest

import AppPaths
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
class RegistrationRestController(
    val userService: UserService,
    val passwordEncoder: BCryptPasswordEncoder,
    val authenticationManager: AuthenticationManager
){

    @PostMapping(AppPaths.USER_REGISTRATION)
    fun registerUserAccount(
        @RequestBody userDto: UserDto,
        request: HttpServletRequest,
    ) {
        val user = userDto.copy(password = passwordEncoder.encode(userDto.password))
        val registered = userService.registerNewUserAccount(user)
        val authToken = UsernamePasswordAuthenticationToken(registered.name, registered.pass)
        authToken.details = WebAuthenticationDetails(request)
        val authentication: Authentication = authenticationManager.authenticate(authToken)
        SecurityContextHolder.getContext().authentication = authentication
    }
}