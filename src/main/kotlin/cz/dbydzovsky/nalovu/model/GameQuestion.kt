package cz.dbydzovsky.nalovu.model

import cz.dbydzovsky.nalovu.model.def.QuestionDefinition
import javax.persistence.*

@Entity(name="game_question")
class GameQuestion (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    val question: QuestionDefinition,

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    val user: User,

    @ManyToOne
    @JoinColumn(name="game_id", nullable=false)
    val game: Game,

    @Column(nullable=false)
    val used: Boolean = false,

    @Column
    val correct: Boolean = false,
)