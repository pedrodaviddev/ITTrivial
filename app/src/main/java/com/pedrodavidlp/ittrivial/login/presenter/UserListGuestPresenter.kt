package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.ExitGame
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList
import com.pedrodavidlp.ittrivial.login.domain.usecase.NotifyStartGame
import com.pedrodavidlp.ittrivial.login.domain.usecase.StartGame
import com.pedrodavidlp.ittrivial.login.router.UserListRouter

class UserListGuestPresenter(getList: GetUserList,
                             exitGame: ExitGame,
                             startGame: StartGame,
                             val notify: NotifyStartGame,
                             router: UserListRouter)
  : UserListPresenter(getList, exitGame, startGame, router),
    UserListContract.Presenter,
    UserListContract.InteractorOutput {

  override fun init() {
    vw.initUI()
    this.getPlayerList(this.getCurrentGame())
    notify.notifyStart(getCurrentGame(), this)
  }
}
