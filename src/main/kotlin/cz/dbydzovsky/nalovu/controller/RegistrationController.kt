package cz.dbydzovsky.nalovu.controller

import AppPaths
import cz.dbydzovsky.nalovu.data.UserDto
import cz.dbydzovsky.nalovu.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import javax.servlet.http.HttpServletRequest
import javax.websocket.server.PathParam


@RestController
class RegistrationController(
    val userService: UserService,
    val passwordEncoder: BCryptPasswordEncoder,
    val authenticationManager: AuthenticationManager
){

    @PostMapping(AppPaths.USER_REGISTRATION)
    fun registerUserAccount(
        @RequestBody userDto: UserDto,
        request: HttpServletRequest,
    ): String {
        val user = userDto.copy(password = passwordEncoder.encode(userDto.password))
        val registered = userService.registerNewUserAccount(user)
        val authToken = UsernamePasswordAuthenticationToken(registered.name, registered.pass)
        authToken.details = WebAuthenticationDetails(request)
        val authentication: Authentication = authenticationManager.authenticate(authToken)
        SecurityContextHolder.getContext().authentication = authentication
        return "redirect:/login"
    }
}