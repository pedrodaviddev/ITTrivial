package com.pedrodavidlp.ittrivial.game.data

import android.os.Handler
import android.os.Looper
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository

class MockGameRepository : GameRepository {
  override fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput) {
    callback.onGetScores(listOf(
        Player("Axel Rose", true, true, false, true, true),
        Player("Adam Gontier", true, false, true, true, true),
        Player("Hayley Williams", true, true, true, false, true)
    ))
  }

  override fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput) {
    callback.onChangeTurn(Player("Axel Rose"))
    Handler(Looper.getMainLooper()).postDelayed(
        { callback.onChangeTurn(Player("Axel Rose", true, true, false, true, true)) }
        , 10000)
    Handler(Looper.getMainLooper()).postDelayed(
        { callback.onChangeTurn(Player("Adam Gontier", true, false, true, true, true)) }
        , 20000)
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onMyTurn()
    }, 30000)

  }

  override fun loseTurnInGame(game: Game) {
    //In mock this do nothing
  }
}