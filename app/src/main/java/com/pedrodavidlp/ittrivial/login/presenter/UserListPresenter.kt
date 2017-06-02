package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.ExitGame
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList
import com.pedrodavidlp.ittrivial.login.domain.usecase.StartGame
import com.pedrodavidlp.ittrivial.login.router.UserListRouter

class UserListPresenter(val getList: GetUserList,
                        val exitGame: ExitGame,
                        val startGame: StartGame,
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

  override fun onFetchUserListSuccess(list: List<Player>) {
    vw.onLoadList(list)
  }

  override fun onError() {
    vw.showError("Error")
  }

  override fun onInitAndMyTurn() {
    router.goToGame(true)
  }

  fun getCurrentGame(): Game {
    return Session.game
  }

  override fun onInitAndWait() {
    router.goToGame(false)
  }

  fun exitGame() {
    exitGame.exitGame(getCurrentGame(), this)
  }

  fun initGame() {
    startGame.startGame(getCurrentGame(), this)
  }
}
