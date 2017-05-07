package com.pedrodavidlp.ittrivial.game.data

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository

class MockQuestionRepository : QuestionRepository {
  override fun loadQuestion(callback: QuestionContract.InteractorOutput) {
    callback.onQuestionLoaded(Question("Porque conrado es tan hipster",
        "Porque si",
        "Porque puede",
        "Por su cicatriz",
        "Porque su novia no le deja trabajar"))
  }

}