package cz.dbydzovsky.nalovu.model.def

import javax.persistence.*

@Entity(name="question_definition")
class QuestionDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null

    @Column
    val type: QuestionType = QuestionType.Choose

    @Column
    val name: String = ""

    @ManyToOne
    @JoinColumn(name="group_definition_id", nullable=true)
    val groupDefinition: GroupDefinition? = null

    @OneToMany(mappedBy="questionDefinition")
    val answerDefinitions: List<AnswerDefinition> = listOf()
}

enum class QuestionType {
    Choose,
    Answer
}