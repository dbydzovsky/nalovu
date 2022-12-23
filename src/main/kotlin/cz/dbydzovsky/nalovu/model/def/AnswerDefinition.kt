package cz.dbydzovsky.nalovu.model.def

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity(name="answer_definition")
class AnswerDefinition (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,

    @Column
    val text: String = "",

    @Column(name="is_correct")
    val isCorrect: Boolean = false,

    @ManyToOne
    @JoinColumn(name="question_definition_id", nullable=false)
    val questionDefinition: QuestionDefinition
)
