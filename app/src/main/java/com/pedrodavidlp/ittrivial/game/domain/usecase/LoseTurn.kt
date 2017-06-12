package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import kotlin.concurrent.thread

class LoseTurn(private val repository: GameRepository) {
  fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput) {
    thread {
      repository.loseTurnInGame(game, callback)
    }
  }
}