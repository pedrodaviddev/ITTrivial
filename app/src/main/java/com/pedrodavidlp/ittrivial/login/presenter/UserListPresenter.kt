package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.usecase.ExitGame
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList
import com.pedrodavidlp.ittrivial.login.router.UserListRouter

class UserListPresenter(val getList: GetUserList,
                        val exitGame: ExitGame,
                        val router: UserListRouter) : UserListContract.Presenter, UserListContract.InteractorOutput {

  lateinit private var vw: UserListContract.View

  override fun init() {
    vw.initUI()
    this.getPlayerList(this.getCurrentGame())
  }

  override fun setView(view: UserListContract.View) {
    this.vw = view
  }

  private fun getPlayerList(game: Game) {
    getList.getUserList(game, this)
  }

  override fun onFetchUserListSuccess(list: List<User>) {
    vw.onLoadList(list)
  }

  override fun onError() {
    vw.showError("Error")
  }

  override fun onInitGame() {
    router.goToGameActivity()
  }

  fun getCurrentGame(): Game {
    return Session.game
  }

  fun exitGame() {
    exitGame.exitGame(getCurrentGame(), this)
  }
}
