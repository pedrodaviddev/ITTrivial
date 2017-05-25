package com.pedrodavidlp.ittrivial.login.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game

class GameListContract {
  interface View {
    fun onLoadList(gameList: List<Game>)
    fun showError(message: String)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
  }

  interface Interactor {
    fun getGameList(callback: GameListContract.InteractorOutput)
  }

  interface InteractorOutput {
    fun onFetchGameListSuccess(gameList: List<Game>)
    fun onFetchGameListError()
    fun onJoinGame(game: Game)
    fun onError()
  }

  interface Router {
    fun goToPlayerGameList()
  }
}