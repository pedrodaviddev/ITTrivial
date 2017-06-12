package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.usecase.EndGame
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList

class WaitPresenter(val turn: GetTurn,
                    val leave: LeaveGame,
                    val end: EndGame,
                    val players: GetUserList,
                    val router: WaitRouter) :
    WaitContract.Presenter,
    WaitContract.InteractorOutput,
    UserListContract.InteractorOutput {

  override fun onLeaveGame() {
    router.leaveGame()
  }

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
    this.listenGameEnds()
  }

  private fun listenGameEnds() {
    end.addListenerToEndGame(this)
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

  override fun onGameEnds() {

  }

  fun leaveGame() {
    leave.leaveGame(Player(Session.username, false), Session.game, this)
  }
}
