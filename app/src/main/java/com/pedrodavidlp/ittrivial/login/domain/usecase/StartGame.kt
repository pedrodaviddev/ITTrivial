package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class StartGame(private val repository: LobbyRepository) {
  fun startGame(game: Game, callback: UserListContract.InteractorOutput) {
    thread {
      repository.startGame(game, callback)
    }
  }
}