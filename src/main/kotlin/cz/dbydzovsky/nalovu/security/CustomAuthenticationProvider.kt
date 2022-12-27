package cz.dbydzovsky.nalovu.security

import cz.dbydzovsky.nalovu.services.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component


@Component
class CustomAuthenticationProvider(
    private val userService: UserService,
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        val name: String = authentication.name
        val password: String = authentication.credentials.toString()
        val user = userService.getUser(name) ?: return null
        // and authenticate against the third-party system
        if (user.pass == password) {
            val authorities = userService.getGrantedAuthorities(user)
            return UsernamePasswordAuthenticationToken(
                name, password, authorities
            )
        }
        return null
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}