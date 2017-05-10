package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList
import com.pedrodavidlp.ittrivial.login.router.UserListRouter

class UserListPresenter(val useCase: GetUserList, val router: UserListRouter) : UserListContract.Presenter, UserListContract.InteractorOutput {
  lateinit private var vw: UserListContract.View
  override fun init() {
    this.getPlayerList(Game("mock"))
  }

  override fun setView(view: UserListContract.View) {
    this.vw = view
  }

  private fun getPlayerList(game: Game) {
    useCase.getPlayerList(game, this)
  }

  override fun onGetSuccess(list: List<User>) {
   vw.onLoadList(list)
  }

  override fun onGetError() {
    vw.showError("Error")
  }

  override fun onInitGame() {
    router.goToGameActivity()
  }
}