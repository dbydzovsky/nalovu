package cz.dbydzovsky.nalovu.security

import cz.dbydzovsky.nalovu.repositories.UserRepository
import cz.dbydzovsky.nalovu.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class MyUserDetailsService (
    private val userService: UserService
        ): UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getUser(username)
            ?: throw UsernameNotFoundException("No user found with username: $username")
        val enabled = true
        val accountNonExpired = true
        val credentialsNonExpired = true
        val accountNonLocked = true
        val authorities = userService.getGrantedAuthorities(user)
        return User(
            user.name, user.pass, enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked, authorities)
    }
}