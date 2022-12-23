package cz.dbydzovsky.nalovu.repositories

import cz.dbydzovsky.nalovu.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByName(username: String): User?
}