package com.pedrodavidlp.ittrivial.login.data

import android.os.Handler
import android.os.Looper
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

class MockLobbyRepository: LobbyRepository {
  override fun createGame(admin: User, callback: MenuContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun exitGame(game: Game, callback: UserListContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun enterGame(game: Game, callback: GameListContract.InteractorOutput) {

  }

  override fun getGames(callback: GameListContract.InteractorOutput) {
    callback.onFetchGameListSuccess(listOf(Game("Solo hay una partida")))
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onFetchGameListSuccess(listOf(Game("Eyy compañeras"),
          Game("Hola prhema"), Game("Como estais locos")))
    }, 15000)
  }

  override fun getUsersInGame(game: Game, callback: UserListContract.InteractorOutput) {
//    callback.onFetchUserListSuccess(listOf(User("franlo")))
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onFetchUserListSuccess(listOf(User("franlo"), User("cotel")))
    }, 7000)
    Handler(Looper.getMainLooper()).postDelayed({
      callback.onFetchUserListSuccess(listOf(User("franlo"), User("cotel"), User("nhemesy")))
    }, 15000)
    Handler(Looper.getMainLooper()).postDelayed({
      this.onInitGame(Game("hola"),callback)
    }, 17000)
  }

  override fun onInitGame(game: Game, callback: UserListContract.InteractorOutput) {
    callback.onInitGame()
  }
}