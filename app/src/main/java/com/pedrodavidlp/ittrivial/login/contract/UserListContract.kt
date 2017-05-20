package com.pedrodavidlp.ittrivial.login.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.domain.model.User

class UserListContract {

  interface View {
    fun showError(message: String)
    fun onLoadList(list: List<User>)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
  }

  interface Interactor {
    fun getUserList(game: Game, callback: UserListContract.InteractorOutput)
  }

  interface InteractorOutput {
    fun onFetchUserListSuccess(playerList: List<User>)
    fun onGetError()
    fun onInitGame()
  }

  interface Router {
    fun goToGameActivity()
  }
}