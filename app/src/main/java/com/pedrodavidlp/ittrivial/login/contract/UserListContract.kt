package com.pedrodavidlp.ittrivial.login.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player

class UserListContract {

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
    fun getUserList(game: Game, callback: UserListContract.InteractorOutput)
  }

  interface InteractorOutput {
    fun onFetchUserList(list: List<Player>)
    fun onError()
    fun onInitAndMyTurn()
    fun onInitAndWait()
  }

  interface Router {
    fun goToGame(myTurn: Boolean)
  }
}