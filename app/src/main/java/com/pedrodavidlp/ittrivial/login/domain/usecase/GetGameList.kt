package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.pattern.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

open class GetGameList(val repository: LobbyRepository) :
    GameListContract.Interactor {
  lateinit var callback: GameListContract.InteractorOutput

  override fun getGameList(callback: GameListContract.InteractorOutput) {
    this.callback = callback
    thread {
      repository.getGames(object : Observer<List<Game>> {
        override fun onValueChange(newValue: List<Game>, oldValue: List<Game>) {
          var listGames = showOnlyNotStartedGames(newValue)
          listGames = showOnlyWithLessThanSixPlayers(listGames)
          callback.onFetchGameListSuccess(listGames)
        }
      })
    }
  }

  protected fun showOnlyWithLessThanSixPlayers(listGames: List<Game>): List<Game> {
    return listGames.filter { it.numPlayers < 6 }
  }

  protected fun showOnlyNotStartedGames(listGames: List<Game>): List<Game> {
    return listGames.filter(Game::nonStarted)
  }
}