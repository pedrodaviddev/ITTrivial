package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository

class EndGame(private val repository: GameRepository) {
  fun addListenerToEndGame(callback: WaitContract.InteractorOutput) {
    repository.listenEndGame(callback)
  }

}