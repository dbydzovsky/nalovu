package cz.dbydzovsky.nalovu.model.def

import javax.persistence.*

@Entity(name = "game_definition")
class GameDefinition (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,

    @OneToMany(mappedBy="gameDefinition")
    val questions: List<QuestionDefinition>
)