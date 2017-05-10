package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class GetGameList(val repository: LobbyRepository) : GameListContract.Interactor {
  override fun getGameList(callback: GameListContract.InteractorOutput) {
    thread {
      repository.getGames(object : Observer<List<Game>> {
        override fun onChange(newValue: List<Game>) {
          callback.onFetchGameListSuccess(newValue)
        }
      })

    }
  }
}