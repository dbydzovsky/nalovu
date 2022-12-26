package cz.dbydzovsky.nalovu.model

import cz.dbydzovsky.nalovu.data.UserRole
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
    val id: Int?=null,

    @Column
    val name: String,

    @OneToMany(mappedBy="game", targetEntity = GameAssignment::class)
    var assignments: MutableList<GameAssignment> = mutableListOf(),

    @OneToMany(mappedBy="game", targetEntity = GameQuestion::class)
    var questions: List<GameQuestion> = mutableListOf()

) {
    val adminAssignment: GameAssignment
        get() = assignments.single { it.role == UserRole.Admin }

    val hunterAssignment: GameAssignment?
        get() = assignments.firstOrNull { it.role == UserRole.Hunter }

    val playerAssignments: List<GameAssignment>
        get() = assignments.filter { it.role == UserRole.Player }

    val hasHunter: Boolean
        get() = assignments.any { it.role == UserRole.Hunter }
}
