package cz.dbydzovsky.nalovu.model

import com.fasterxml.jackson.annotation.JsonIgnore
import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.services.GameService
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany


@Entity(name="game")
class Game (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column
    val name: String,

    @OneToMany(mappedBy="game", targetEntity = GameAssignment::class)
    var assignments: MutableList<GameAssignment> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy="game", targetEntity = GameQuestion::class)
    var questions: List<GameQuestion> = mutableListOf()

) {

    val freeSlots: Int
        get() = GameService.MAX_PLAYERS - getPlayerAssignments().size

    val hasHunter: Boolean
        get() = assignments.any { it.role == UserRole.Hunter }
}

fun Game.getAdminAssignment():GameAssignment {
    return assignments.single { it.role == UserRole.Admin }
}
fun Game.getHunterAssignment(): GameAssignment? {
    return assignments.firstOrNull { it.role == UserRole.Hunter }
}

fun Game.getPlayerAssignments(): List<GameAssignment> {
    return assignments.filter { it.role == UserRole.Player }
}

