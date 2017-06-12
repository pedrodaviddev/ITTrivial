package com.pedrodavidlp.ittrivial.game.router

import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment

class QuestionRouter(private val fragment: QuestionFragment) : QuestionContract.Router {
  override fun goToGame() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(fragment.activity as GameActivity)
    parentRouter.goToRoulette()
  }

  override fun goToWait() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(fragment.activity as GameActivity)
    parentRouter.goToWait()
  }
}