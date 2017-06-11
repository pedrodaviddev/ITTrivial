package com.pedrodavidlp.ittrivial.game.presenter

import android.widget.ImageView
import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.Category.*
import com.pedrodavidlp.ittrivial.game.view.activity.transition.*

class GamePresenter(val repository: GameRepository,
                    val leaveGame: LeaveGame,
                    val router: GameRouter) : GameContract.Presenter, GameContract.InteractorOutput {

  lateinit var vw: GameContract.View

  override fun init() {
    vw.initUi()
    this.getScores()
  }

  override fun setView(view: GameContract.View) {
    vw = view
  }

  override fun getScores() {

  }

  override fun onGetScores(playerList: List<Player>) {
    vw.loadList(playerList)
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


  private fun getCurrentGame(): Game {
    return Session.game
  }

  fun manageTurn(isMyTurn: Boolean) {
    if (!isMyTurn)
      router.goToWait()
  }

  fun manageBack(resultCode: Int) {
    when (resultCode) {
      WaitRouter.RESULT_LEAVE -> router.goToMenu()
    }
  }
}
