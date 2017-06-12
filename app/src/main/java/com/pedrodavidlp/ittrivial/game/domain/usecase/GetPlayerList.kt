package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.base.pattern.Observer
import com.pedrodavidlp.ittrivial.game.contract.PlayerListContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.concurrent.thread

class GetPlayerList(val repository: LobbyRepository) : PlayerListContract.Interactor {
  override fun getUserList(game: Game, callback: PlayerListContract.InteractorOutput) {
    thread {
      repository.getUsersInGame(game, object : Observer<List<Player>> {
        override fun onValueChange(newValue: List<Player>, oldValue: List<Player>) {
          val winner = getWinnerPlayer(newValue)
          if (winner != null) {
            callback.gameFinished(winner)
          } else {
            callback.onFetchPlayerList(newValue)
          }
        }
      })
    }
  }

  private fun getWinnerPlayer(listPlayers: List<Player>): Player? {
    return listPlayers
        .filter {
          it.enterprise &&
              it.hardware &&
              it.history &&
              it.network &&
              it.software
        }.firstOrNull()
  }
}