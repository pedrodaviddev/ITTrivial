package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.EnterGame
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetGameList
import com.pedrodavidlp.ittrivial.login.router.GameListRouter

class GameListPresenter(private val gameList: GetGameList,
                        private val enterGame: EnterGame,
                        private var router: GameListRouter) :
    GameListContract.Presenter,
    GameListContract.InteractorOutput {

  lateinit var vw: GameListContract.View

  override fun init() {
    gameList.getGameList(this)
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
    Session.game = game
    router.goToPlayerGameList()
  }

  override fun onError() {

  }

  fun enterGame(game: Game) {
    enterGame.enterGame(game, this)
  }
}
