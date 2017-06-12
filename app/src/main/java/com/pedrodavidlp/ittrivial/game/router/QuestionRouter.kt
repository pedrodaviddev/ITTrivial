package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment

class QuestionRouter(private val fragment: QuestionFragment) : QuestionContract.Router {
  override fun goToGame() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(GameActivity.instance)
    Handler().postDelayed({
      parentRouter.goToRoulette()
    }, 400)
  }

  override fun goToWait() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(GameActivity.instance)
    Handler().postDelayed({
      parentRouter.goToWait()
    }, 600)
  }
}