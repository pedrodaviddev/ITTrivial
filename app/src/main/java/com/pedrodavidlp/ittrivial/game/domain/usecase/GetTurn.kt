package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.base.pattern.Observer
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import kotlin.concurrent.thread

class GetTurn(val repository: GameRepository) : WaitContract.Interactor {
  override fun getTurn(game: Game, callback: WaitContract.InteractorOutput) {
    thread {
      repository.getTurnInGame(game, object : Observer<Player> {
        override fun onValueChange(newValue: Player, oldValue: Player) {
          if (newValue.username == Session.player.username) {
            callback.onMyTurn()
          } else if (newValue.username != oldValue.username) {
            callback.onChangeTurn(newValue)
          }
        }

      })
    }
  }
}