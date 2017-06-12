package com.pedrodavidlp.ittrivial.game.router

import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment

class WaitRouter(private val fragment: WaitFragment) : WaitContract.Router {
  override fun goToRoulette() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(fragment.activity as GameActivity)
    parentRouter.goToRoulette()
  }
}