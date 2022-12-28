package cz.dbydzovsky.nalovu.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name ="money_fight")
class MoneyFight (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column
    var correctAnswers: Int,

    @Column
    var amount: Long
)