package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class EnterGame(val repository: LobbyRepository) {
  fun enterGame(game: Game, callback: GameListContract.InteractorOutput) {
    thread {
      repository.enterGame(game, callback)
    }
  }
}

