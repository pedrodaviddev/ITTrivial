package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class GetGames(val repository: LobbyRepository): GameListContract.Interactor {
  override fun getGameList(callback: GameListContract.InteractorOutput) {
    thread {
      repository.getGames(callback)
    }
  }
}