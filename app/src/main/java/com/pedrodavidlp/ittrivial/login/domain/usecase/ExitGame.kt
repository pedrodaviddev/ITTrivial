package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class ExitGame(val repository: LobbyRepository) {
  fun exitGame(game: Game, callback: UserListContract.InteractorOutput) {
    thread {
      repository.exitGame(game, callback)
    }
  }
}