package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.game.domain.Question

class QuestionContract {
  interface View {
    fun showError(message: String)
    fun onLoadQuestion(question: Question)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun onLogin(username: String)
  }

  interface Interactor {
    fun getQuestion()
  }

  interface InteractorOutput {
    fun onQuestionLoaded(question: Question)
  }

  interface Router {
    fun goToGame()
    fun goToWait()
  }
}