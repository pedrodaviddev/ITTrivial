package com.pedrodavidlp.ittrivial.game.router

import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.view.activity.FinishGameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment
import org.jetbrains.anko.startActivity

class WaitRouter(private val fragment: WaitFragment) : WaitContract.Router {
  override fun goToFinishGame(winner: String?) {
    fragment.activity.startActivity<FinishGameActivity>(Pair("winner", winner!!))
    fragment.activity.supportFragmentManager.popBackStackImmediate()
    fragment.activity.finish()
  }

  override fun goToRoulette() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(GameActivity.instance)
    parentRouter.goToRoulette()
  }
}