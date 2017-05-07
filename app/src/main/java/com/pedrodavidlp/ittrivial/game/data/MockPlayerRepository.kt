package com.pedrodavidlp.ittrivial.game.data

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.repository.PlayerRepository
import com.pedrodavidlp.ittrivial.login.domain.model.Player

class MockPlayerRepository : PlayerRepository {
  override fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput) {
    val one = Player("Franlo", 20)
    val two = Player("Nhemesy", 200)
    val three = Player("Conri", 45)
    Handler().postDelayed(
        {
          callback.onGetScores(listOf(one, two, three))
        }
        , 1000)

  }
}