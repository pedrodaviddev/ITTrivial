package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter

class QuestionPresenter(val repository: QuestionRepository, val router: QuestionRouter) :
    QuestionContract.Presenter, QuestionContract.InteractorOutput {
  lateinit var viper: QuestionContract.View

  override fun init() {
    this.getQuestion()
  }

  override fun setView(view: QuestionContract.View) {
    this.viper = view
  }

  override fun getQuestion() {
    repository.loadQuestion(this)
  }

  override fun onError() {
    viper.showError("Error cargando pregunta")
  }

  override fun onQuestionLoaded(question: Question) {
    viper.onLoadQuestion(question)
  }

  fun fail() {
    router.goToWait()
  }

  fun hit() {
    router.goToGame()
  }
}