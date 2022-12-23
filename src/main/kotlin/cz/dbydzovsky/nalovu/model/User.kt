package cz.dbydzovsky.nalovu.model

import cz.dbydzovsky.nalovu.data.UserRole
import javax.persistence.*


@Entity(name="my_user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null,

    @Column(unique = true, nullable = false)
    val name: String,

    @Column
    val pass: String,

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    val assignments: List<GameAssignment> = listOf()
)
