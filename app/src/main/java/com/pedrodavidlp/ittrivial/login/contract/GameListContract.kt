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
    fun onFetchGameList()
  }

  interface Interactor {
    fun getGameList()
  }

  interface InteractorOutput {
    fun onFetchGameListSuccess(gameList: List<Game>)
    fun onFetchGameListError()
    fun onJoinGame(game: Game)
  }

  interface Router {
    fun goToPlayerGameList()
  }
}