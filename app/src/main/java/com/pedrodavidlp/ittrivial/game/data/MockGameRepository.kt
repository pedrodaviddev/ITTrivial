package com.pedrodavidlp.ittrivial.game.data

import android.os.Handler
import android.os.Looper
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.PlayerListContract
import com.pedrodavidlp.ittrivial.login.domain.GameRepository

class MockGameRepository : GameRepository {
  override fun getUsersInGame(game: Game, callback: PlayerListContract.InteractorOutput) {
    callback.onGetSuccess(listOf(
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

  }

  override fun getGameList(callback: GameListContract.InteractorOutput) {
    callback.onFetchGameListSuccess(
        listOf(
            Game("Hola"),
            Game("Que tal"),
            Game("Yo bien"),
            Game("Jajjaja"),
            Game("que ase"),
            Game("biba"),
            Game("mock")

        ))
  }
}