package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

class CreateGame(private val repository: LobbyRepository) {
  fun createGame(admin: User, callback: MenuContract.InteractorOutput) {
    repository.createGame(admin, callback)
  }
}