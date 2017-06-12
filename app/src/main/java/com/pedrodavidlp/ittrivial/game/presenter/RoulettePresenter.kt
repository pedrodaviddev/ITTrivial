package com.pedrodavidlp.ittrivial.game.presenter

import android.widget.ImageView
import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.Category.*
import com.pedrodavidlp.ittrivial.game.view.activity.transition.*

class RoulettePresenter(val repository: GameRepository,
                        val router: GameRouter) : RouletteContract.Presenter, RouletteContract.InteractorOutput {

  lateinit var vw: RouletteContract.View

  override fun init() {

    if (Session.player.isWinner()) {
      router.goToFinish(Session.player)
    } else {
      vw.initUi()

      vw.initMedals(Session.player.hardware,
          Session.player.software,
          Session.player.network,
          Session.player.enterprise,
          Session.player.history)
    }
  }

  override fun setView(view: RouletteContract.View) {
    vw = view
  }

  fun goToQuestion(category: Category, image: ImageView) {
    val transition: TransitionTemplate =
        when (category) {
          HISTORY -> TransitionHistory(image, router)
          HARDWARE -> TransitionHardware(image, router)
          SOFTWARE -> TransitionSoftware(image, router)
          ENTERPRISE -> TransitionEnterprise(image, router)
          NETWORK -> TransitionNetwork(image, router)
        }
    transition.makeTransition()
  }
}
