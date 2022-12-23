package cz.dbydzovsky.nalovu.controller

import cz.dbydzovsky.nalovu.data.UserDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.context.request.WebRequest

@Controller
class LoginController {

    @GetMapping("/")
    fun showIndex(request: WebRequest, model: Model): String {
        return "index.html"
    }

    @GetMapping("/login")
    fun showLogin(request: WebRequest, model: Model): String {
        return "login.html"
    }
}