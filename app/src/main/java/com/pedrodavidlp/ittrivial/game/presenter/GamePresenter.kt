package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.view.*
import com.pedrodavidlp.ittrivial.game.view.Category.*

class GamePresenter(val repository: GameRepository) : GameContract.Presenter, GameContract.InteractorOutput {
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

  fun makeTransitionTo(category: Category) {
    val transition: TransitionTemplate = when(category) {
      HISTORY -> TransitionHistory()
      HARDWARE -> TransitionHardware()
      SOFTWARE -> TransitionSoftware()
      ENTERPRISE -> TransitionEnterprise()
      NETWORK -> TransitionNetwork()
    }
  }

}