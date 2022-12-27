package cz.dbydzovsky.nalovu.model.def

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name="group_definition")
class GroupDefinition (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column
    val name: String = "",

    @JsonIgnore
    @OneToMany(mappedBy="groupDefinition", targetEntity = QuestionDefinition::class)
    val questionDefinitions: List<QuestionDefinition> = listOf()
)