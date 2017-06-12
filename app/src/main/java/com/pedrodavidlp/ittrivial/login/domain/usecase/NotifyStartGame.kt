package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.domain.data.Observer
import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

class NotifyStartGame(private val repository: LobbyRepository) {
  fun notifyStart(game: Game, callback: UserListContract.InteractorOutput) {
    repository.getCurrentActivePlayer(game, object : Observer<Player?> {
      override fun onValueChange(newValue: Player?, oldValue: Player?) {
        newValue?.let {
          if (Session.username == it.username) {
            callback.onInitAndMyTurn()
          } else {
            callback.onInitAndWait()
          }
        }
      }
    })
  }
}