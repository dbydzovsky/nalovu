package cz.dbydzovsky.nalovu.model.def

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name="question_definition")
class QuestionDefinition (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column
    val type: QuestionType = QuestionType.Choose,

    @Column
    val name: String,

    @ManyToOne
    @JoinColumn(name="group_definition_id", nullable=true)
    val groupDefinition: GroupDefinition? = null,

    @JsonIgnore
    @OneToMany(mappedBy="questionDefinition", targetEntity= AnswerDefinition::class)
    val answerDefinitions: List<AnswerDefinition> = listOf(),

    @ManyToOne
    @JoinColumn(name="game_definition_id", nullable=false)
    val gameDefinition: GameDefinition
)

enum class QuestionType {
    Choose,
    Answer
}