package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import kotlin.concurrent.thread

class GetTurn(val repository: GameRepository) : WaitContract.Interactor {
  override fun getTurn(game: Game, callback: WaitContract.InteractorOutput) {
    thread {
      repository.getTurnInGame(game, callback)
    }
  }
}