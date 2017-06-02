package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

class CreateGame(private val repository: LobbyRepository) {
  fun createGame(admin: Player, callback: MenuContract.InteractorOutput) {
    repository.createGame(admin, callback)
  }
}