package com.pedrodavidlp.ittrivial.game.presenter

import android.widget.ImageView
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.Category.*
import com.pedrodavidlp.ittrivial.game.view.activity.transition.*

class RoulettePresenter(val repository: GameRepository,
                        val leaveGame: LeaveGame,
                        val router: RouletteRouter) : RouletteContract.Presenter, RouletteContract.InteractorOutput {

  lateinit var vw: RouletteContract.View

  override fun init() {
    vw.initUi()
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
