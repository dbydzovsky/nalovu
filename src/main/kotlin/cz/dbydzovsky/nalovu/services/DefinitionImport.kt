package cz.dbydzovsky.nalovu.services

import cz.dbydzovsky.nalovu.repositories.AnswerDefinitionRepository
import cz.dbydzovsky.nalovu.repositories.QuestionDefinitionRepository
import org.springframework.stereotype.Service

@Service
class DefinitionImport(
    private val gameService: GameService,
    private val answerDefinitionRepository: AnswerDefinitionRepository,
    private val questionDefinitionRepository: QuestionDefinitionRepository
) {

    fun import() {

    }
}