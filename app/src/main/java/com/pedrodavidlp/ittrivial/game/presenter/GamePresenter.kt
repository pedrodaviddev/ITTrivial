package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.PlayerRepository
import com.pedrodavidlp.ittrivial.login.domain.model.User

class GamePresenter(val repository: PlayerRepository) : GameContract.Presenter, GameContract.InteractorOutput {
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

}