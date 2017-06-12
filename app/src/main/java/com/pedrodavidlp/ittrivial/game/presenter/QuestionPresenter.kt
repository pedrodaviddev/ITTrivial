package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetQuestion
import com.pedrodavidlp.ittrivial.game.domain.usecase.LoseTurn
import com.pedrodavidlp.ittrivial.game.domain.usecase.WinCategory
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category

class QuestionPresenter(val question: GetQuestion,
                        val win: WinCategory,
                        val lose: LoseTurn,
                        val router: GameRouter) :
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
    lose.loseTurnInGame(Session.game, this)
    router.goToWait()
  }

  fun hit(category: Category) {
    vw.stopCounter()
    win.winCategory(Session.game, Session.player.username, category)
    router.goToRoulette()
  }

  override fun loseTurn() {
    router.goToWait()
  }
}
