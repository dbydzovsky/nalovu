package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.data.UserDto
import cz.dbydzovsky.nalovu.model.User
import cz.dbydzovsky.nalovu.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class UserService(
    private val userRepository: UserRepository,
    ) {

    fun registerNewUserAccount(userDto: UserDto): User {
        val user = User(name=userDto.username, pass = userDto.password)
        return userRepository.save(user)
    }

    fun getUser(username: String): User? {
        return userRepository.findByName(username)
    }

    @Transactional
    fun getGrantedAuthorities(user: User, gameId: Int): List<GrantedAuthority> {
        val gameAssignment = user.assignments.find { it.id == gameId }
            ?: return emptyList()
        return listOf(SimpleGrantedAuthority(gameAssignment.role.name))
    }
}