package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.login.domain.model.User

class WaitPresenter(val useCase: GetTurn) : WaitContract.Presenter, WaitContract.InteractorOutput {

  lateinit var v: WaitContract.View

  override fun init() {
    this.getTurn()
  }

  override fun setView(view: WaitContract.View) {
    this.v = view
  }

  override fun getTurn() {
    useCase.getTurn(Game("mock"), this)
  }

  override fun onMyTurn() {
    v.myTurn()
  }

  override fun onChangeTurn(player: Player) {
    v.changeTurn(player)
  }

  override fun getCategory() {
    useCase.getCategory(Game("mock"), this)
  }
}
