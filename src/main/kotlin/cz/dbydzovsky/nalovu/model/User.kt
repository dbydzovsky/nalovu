package cz.dbydzovsky.nalovu.model

import com.fasterxml.jackson.annotation.JsonIgnore
import cz.dbydzovsky.nalovu.data.UserRole
import javax.persistence.*


@Entity(name="my_user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column(unique = true, nullable = false)
    val name: String,

    @JsonIgnore
    @Column
    val pass: String,

    @JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    val assignments: List<GameAssignment> = listOf()
)
