package com.pedrodavidlp.ittrivial.game.router

import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.activity.FinishGameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment
import org.jetbrains.anko.startActivity

class WaitRouter(private val fragment: WaitFragment) : WaitContract.Router {
  override fun goToFinishGame(winner: Player) {
    fragment.activity.startActivity<FinishGameActivity>(Pair("winner", winner.username))
    fragment.activity.supportFragmentManager.popBackStackImmediate()
    fragment.activity.finish()
  }

  override fun goToRoulette() {
    val parentRouter: GameRouter = ServiceLocator.provideGameRouter(fragment.activity as GameActivity)
    parentRouter.goToRoulette()
  }
}