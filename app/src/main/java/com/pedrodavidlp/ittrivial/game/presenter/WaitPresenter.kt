package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.PlayerListContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetPlayerList
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.router.GameRouter

class WaitPresenter(private val turn: GetTurn,
                    private val players: GetPlayerList,
                    private val router: GameRouter) :
    WaitContract.InteractorOutput,
    PlayerListContract.InteractorOutput {

  lateinit private var v: WaitContract.View


  fun setView(view: WaitContract.View) {
    this.v = view
  }

  fun init() {
    this.getTurn()
    this.getPlayers()
  }

  override fun onMyTurn() {
    v.myTurn()
    router.goToRoulette()
  }

  override fun onChangeTurn(player: Player) {
    v.changeTurn(player)
  }

  private fun getTurn() {
    turn.getTurn(Session.game, this)
  }

  private fun getPlayers() {
    players.getUserList(Session.game, this)
  }


  override fun gameFinished(winner: Player) {
    router.goToFinish(winner)
  }

  override fun onFetchPlayerList(newValue: List<Player>) {
    v.showListPlayers(newValue)
  }

  override fun onLeaveGame() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onError() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
