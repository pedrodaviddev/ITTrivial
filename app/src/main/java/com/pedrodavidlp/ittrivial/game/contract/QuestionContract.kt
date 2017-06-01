package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.view.Category

class QuestionContract {
  interface View {
    fun showError(message: String)
    fun onLoadQuestion(question: Question)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun getQuestion(category: Category)
  }

  interface Interactor {
    fun getQuestion()
  }

  interface InteractorOutput {
    fun onQuestionLoaded(question: Question)
    fun onError()
  }

  interface Router {
    fun goToGame()
    fun goToWait()
  }
}