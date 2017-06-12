package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player

class PlayerListContract {

  interface View {
    fun showError(message: String)
    fun onLoadList(list: List<Player>)
    fun initUI()
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
  }

  interface Interactor {
    fun getUserList(game: Game, callback: PlayerListContract.InteractorOutput)
  }

  interface InteractorOutput {
    fun onError()
    fun gameFinished(winner: Player)
    fun onFetchPlayerList(newValue: List<Player>)
  }

  interface Router {
    fun goToGame(myTurn: Boolean)
  }
}