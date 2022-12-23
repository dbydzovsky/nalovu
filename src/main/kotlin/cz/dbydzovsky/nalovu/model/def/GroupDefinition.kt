package cz.dbydzovsky.nalovu.model.def

import javax.persistence.*

@Entity(name="group_definition")
class GroupDefinition (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,

    @Column
    val name: String = "",

    @OneToMany(mappedBy="groupDefinition")
    val questionDefinitions: List<QuestionDefinition> = listOf()
)