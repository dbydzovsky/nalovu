package cz.dbydzovsky.nalovu.model

import com.fasterxml.jackson.annotation.JsonIgnore
import cz.dbydzovsky.nalovu.data.UserRole
import cz.dbydzovsky.nalovu.model.def.GroupDefinition
import javax.persistence.*

@Entity(name="game_assignment")
class GameAssignment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="game_id", nullable=false)
    val game: Game,

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    val user: User,

    @Column
    val role: UserRole = UserRole.Player,

    @ManyToOne
    @JoinColumn(name="group_id", nullable=true)
    val group: GroupDefinition? = null,
)