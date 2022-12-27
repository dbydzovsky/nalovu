package cz.dbydzovsky.nalovu.model.def

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "game_definition")
class GameDefinition (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    val name: String,

    @JsonIgnore
    @OneToMany(mappedBy="gameDefinition", targetEntity = QuestionDefinition::class)
    var questions: List<QuestionDefinition>
)