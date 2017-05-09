package com.pedrodavidlp.ittrivial.game.data

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.PlayerRepository
import com.pedrodavidlp.ittrivial.login.domain.model.User

class MockPlayerRepository : PlayerRepository {
  override fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput) {
    val one = Player("Franlo")
    val two = Player("Nhemesy")
    val three = Player("Conri")
    Handler().postDelayed(
        {
          callback.onGetScores(listOf(one, two, three))
        }
        , 1000)

  }
}