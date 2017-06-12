package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository
import com.pedrodavidlp.ittrivial.game.view.Category
import kotlin.concurrent.thread

class GetQuestion(private val repository: QuestionRepository) {
  fun getQuestion(category: Category, callback: QuestionContract.InteractorOutput) {
    thread {
      repository.getQuestion(category, callback)
    }
  }

}