package com.pedrodavidlp.ittrivial.game.presenter

import android.widget.ImageView
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.*
import com.pedrodavidlp.ittrivial.game.view.Category.*

class GamePresenter(val repository: GameRepository, val router: GameRouter) : GameContract.Presenter, GameContract.InteractorOutput {
  lateinit var viper: GameContract.View
  override fun init() {
    viper.initUi()
    this.getScores()
  }

  override fun setView(view: GameContract.View) {
    viper = view
  }

  override fun getScores() {
    repository.getPlayersOnGame(Game("prueba"), this)
  }

  override fun onGetScores(playerList: List<Player>) {
    viper.loadList(playerList)
  }

  fun makeTransitionTo(category: Category, image: ImageView) {
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