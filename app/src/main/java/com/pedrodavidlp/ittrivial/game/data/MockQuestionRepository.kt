package com.pedrodavidlp.ittrivial.game.data

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository
import java.util.*

class MockQuestionRepository : QuestionRepository {
  override fun updateScore(player: Player) {
    //this do nothing here
  }

  override fun getQuestion(callback: QuestionContract.InteractorOutput) {
    val random = Random()
    when(random.nextInt(4)){
      0 ->     callback.onQuestionLoaded(Question("Porque conrado es tan hipster",
          "Porque si",
          "Porque puede",
          "Por su cicatriz",
          "Porque su novia no le deja trabajar"))
      1 ->     callback.onQuestionLoaded(Question("Que bien va el tuneup",
          "Bob esponja bote de tomate",
          "La snapcho",
          "Luego me acuerdo que esto esta subido a github",
          "Bueno esta en clave"))
      2 ->     callback.onQuestionLoaded(Question("Ejemplo",
          "Si",
          "No",
          "Quizas",
          "Hey"))
      3 ->     callback.onQuestionLoaded(Question("Hola",
          "besame",
          "que tal",
          "como estas",
          "buenos dias"))
    }

  }

}