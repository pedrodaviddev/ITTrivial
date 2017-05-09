package com.pedrodavidlp.ittrivial.login.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.domain.model.User

class PlayerListContract {

  interface View {
    fun showError(message: String)
    fun onLoadList(list: List<Player>)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun getPlayerList(game: Game)
  }

  interface Interactor {
    fun getPlayerList(game: Game)
  }

  interface InteractorOutput {
    fun onGetSuccess(list: List<Player>)
    fun onGetError()
  }

  interface Router {
    fun goToGameActivity()
  }
}