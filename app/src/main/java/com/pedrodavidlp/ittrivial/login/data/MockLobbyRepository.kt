package com.pedrodavidlp.ittrivial.login.data

import android.os.Handler
import android.os.Looper
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

class MockLobbyRepository : LobbyRepository {
  override fun createGame(admin: Player, callback: MenuContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun startGame(game: Game, callback: UserListContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun exitGame(game: Game, callback: UserListContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun enterGame(game: Game, callback: GameListContract.InteractorOutput) {

  }

  override fun getGames(callback: GameListContract.InteractorOutput) {
    callback.onFetchGameListSuccess(listOf(Game("Solo hay una partida", 4)))
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onFetchGameListSuccess(listOf(Game("Eyy compañeras", 5),
          Game("Hola prhema", 2), Game("Como estais locos", 1)))
    }, 15000)
  }

  override fun getUsersInGame(game: Game, callback: UserListContract.InteractorOutput) {
//    callback.onFetchUserListSuccess(listOf(Player("franlo")))
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onFetchUserListSuccess(listOf(Player("franlo"), Player("cotel")))
    }, 7000)
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onFetchUserListSuccess(listOf(Player("franlo"), Player("cotel"), Player("nhemesy")))
    }, 15000)
    Handler(Looper.getMainLooper()).postDelayed({
      this.onInitGame(Game("hola", 2), callback)
    }, 17000)
  }

  override fun onInitGame(game: Game, callback: UserListContract.InteractorOutput) {
    callback.onInitAndMyTurn()
  }
}