package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
import com.pedrodavidlp.ittrivial.game.view.Category

class QuestionPresenter(val question: QuestionRepository,
                        val game: GameRepository,
                        val router: QuestionRouter) :
    QuestionContract.Presenter, QuestionContract.InteractorOutput {

  lateinit var vw: QuestionContract.View

  override fun init(category: Category) {
    this.getQuestion(category)
  }

  override fun setView(view: QuestionContract.View) {
    this.vw = view
  }

  override fun getQuestion(category: Category) {
    question.getQuestion(category, this)
  }

  override fun onError() {
    vw.showError("Error cargando pregunta")
  }

  override fun onQuestionLoaded(question: Question) {
    vw.onLoadQuestion(question)
  }

  fun fail() {
    vw.stopCounter()
    game.loseTurnInGame(Session.game, this)
  }

  fun hit() {
    vw.stopCounter()
    router.goToGame()
  }

  override fun loseTurn() {
    router.goToWait()
  }
}
