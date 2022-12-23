package cz.dbydzovsky.nalovu.controller

import AppPaths
import cz.dbydzovsky.nalovu.data.UserDto
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.services.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest


@Controller
class RegistrationController(
    val userService: UserService,
    val passwordEncoder: BCryptPasswordEncoder
){


    @GetMapping(AppPaths.USER_REGISTRATION)
    fun showRegistrationForm(request: WebRequest, model: Model): String {
        val userDto = UserDto("", "")
        model.addAttribute("user", userDto)
        return "registration.html"
    }

    @PostMapping(AppPaths.USER_REGISTRATION)
    fun registerUserAccount(
        @ModelAttribute("user") userDto: UserDto,
        request: HttpServletRequest,
        errors: Errors
    ): String {
        val user = userDto.copy(password = passwordEncoder.encode(userDto.password))
        val registered = userService.registerNewUserAccount(user)
        // todo perform login
        return "redirect:/login"
    }
}