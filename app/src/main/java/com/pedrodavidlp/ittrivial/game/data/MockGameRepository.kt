package com.pedrodavidlp.ittrivial.game.data

import android.os.Handler
import android.os.Looper
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.PlayerListContract
import com.pedrodavidlp.ittrivial.login.domain.GameRepository
import com.pedrodavidlp.ittrivial.login.domain.model.Player

class MockGameRepository : GameRepository {
  override fun getUsersInGame(game: Game, callback: PlayerListContract.InteractorOutput) {
    callback.onGetSuccess(listOf(
        Player("Axel Rose", 30),
        Player("Chester Bennington", 40),
        Player("Hayley Williams", 100)
    ))
  }

  override fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput) {
    callback.onChangeTurn(Player("Axel Rose", 40))
    Handler(Looper.getMainLooper()).postDelayed(
        { callback.onChangeTurn(Player("Hueles mal", 20)) }
        , 10000)
    Handler(Looper.getMainLooper()).postDelayed(
        { callback.onChangeTurn(Player("Pepitaaaa", 210)) }
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