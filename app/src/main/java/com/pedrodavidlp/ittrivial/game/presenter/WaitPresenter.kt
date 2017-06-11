package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList

class WaitPresenter(val turn: GetTurn,
                    val players: GetUserList) :
    WaitContract.Presenter,
    WaitContract.InteractorOutput,
    UserListContract.InteractorOutput {
  override fun onFetchUserListSuccess(list: List<Player>) {
    v.showListPlayers(list)
  }

  override fun onError() {

  }

  override fun onInitAndMyTurn() {

  }

  override fun onInitAndWait() {

  }

  lateinit var v: WaitContract.View

  override fun init() {
    this.getTurn()
    this.getPlayers()
  }

  private fun getPlayers() {
    players.getUserList(Session.game, this)
  }

  override fun setView(view: WaitContract.View) {
    this.v = view
  }

  override fun getTurn() {
    turn.getTurn(Session.game, this)
  }

  override fun onMyTurn() {
    v.myTurn()
  }

  override fun onChangeTurn(player: Player) {
    v.changeTurn(player)
  }
}
