package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository

class LeaveGame(private val repository: GameRepository) {
  fun leaveGame(player: Player, currentGame: Game, callback: WaitContract.InteractorOutput) {
    repository.leaveGame(player, currentGame, callback)
  }
}