package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetGameList

class GameListPresenter(val useCase: GetGameList): GameListContract.Presenter, GameListContract.InteractorOutput {
  lateinit var vw: GameListContract.View

  override fun init() {
    useCase.getGameList(this)
  }
  override fun setView(view: GameListContract.View) {
    this.vw = view
  }

  override fun onFetchGameListSuccess(gameList: List<Game>) {
    vw.onLoadList(gameList)
  }

  override fun onFetchGameListError() {
    vw.showError("error")
  }

  override fun onJoinGame(game: Game) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}