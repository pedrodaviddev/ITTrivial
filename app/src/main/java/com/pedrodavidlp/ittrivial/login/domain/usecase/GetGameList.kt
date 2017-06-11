package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.domain.data.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class GetGameList(val repository: LobbyRepository) :
    GameListContract.Interactor {
  lateinit var callback: GameListContract.InteractorOutput

  override fun getGameList(callback: GameListContract.InteractorOutput) {
    this.callback = callback
    thread {
      repository.getGames(object : Observer<List<Game>> {
        override fun onValueChange(newValue: List<Game>, oldValue: List<Game>) {
          val listGames = showOnlyNotStartedGames(newValue)
          callback.onFetchGameListSuccess(listGames)
        }
      })
    }
  }

  private fun showOnlyNotStartedGames(listGames: List<Game>): List<Game> {
    return listGames.filter(Game::nonStarted)
  }
}