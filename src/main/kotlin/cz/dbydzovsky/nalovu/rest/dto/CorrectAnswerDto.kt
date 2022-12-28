package cz.dbydzovsky.nalovu.rest.dto

data class CorrectAnswerDto(
    val userId: Long,
    val questionId: Long,
    val answerId: Long? = null
)