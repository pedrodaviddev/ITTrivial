package com.pedrodavidlp.ittrivial.game.router

import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.RouletteFragment

class RouletteRouter(private val fragment: RouletteFragment) : RouletteContract.Router {

  override fun goToQuestion(category: Category) {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(fragment.activity as GameActivity)
    parentRouter.goToQuestion(category)
  }

  override fun goToMenu() {
  }

  override fun goToWait() {
  }
}