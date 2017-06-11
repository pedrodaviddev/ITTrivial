package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.router.WaitRouter

class WaitPresenter(val turn: GetTurn,
                    val leave: LeaveGame,
                    val router: WaitRouter) : WaitContract.Presenter, WaitContract.InteractorOutput {
  override fun onLeaveGame() {
    router.leaveGame()
  }

  lateinit var v: WaitContract.View

  override fun init() {
    this.getTurn()
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

  fun leaveGame() {
    leave.leaveGame(Player(Session.username, false), Session.game, this)
  }
}
