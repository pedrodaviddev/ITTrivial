package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class GetUserList(val repository: LobbyRepository): UserListContract.Interactor {
  override fun getPlayerList(game: Game, callback: UserListContract.InteractorOutput) {
    thread {
      repository.getUsersInGame(game, object : Observer<List<User>>{
        override fun onChange(newValue: List<User>) {
          callback.onGetSuccess(newValue)
        }

      })
    }
  }
}