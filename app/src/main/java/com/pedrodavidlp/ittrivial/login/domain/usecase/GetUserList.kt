package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.domain.data.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class GetUserList(val repository: LobbyRepository) : UserListContract.Interactor {
  override fun getUserList(game: Game, callback: UserListContract.InteractorOutput) {
    thread {
      repository.getUsersInGame(game, object : Observer<List<Player>> {
        override fun onValueChange(newValue: List<Player>, oldValue: List<Player>) {
          callback.onFetchUserList(newValue)
        }
      })
    }
  }
}