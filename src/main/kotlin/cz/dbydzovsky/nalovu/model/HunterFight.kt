package cz.dbydzovsky.nalovu.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name ="money_fight")
class HunterFight (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column
    val minOffer: Int,

    @Column
    val offer: Int,

    @Column
    val maxOffer: Int,

    @Column
    val chosenOffer: Long,

    @Column
    val remainingQuestions: Int,

    @Column
    val hunterAnsweredCount: Int
) {
    companion object {
        const val QUESTION_COUNT = 7
    }

    val hunterWon: Boolean
        get() = (hunterAnsweredCount + remainingQuestions) > QUESTION_COUNT

    val playerWon : Boolean
        get() = !hunterWon && remainingQuestions == 0

    val finished: Boolean
        get() = remainingQuestions == 0 || hunterWon
}