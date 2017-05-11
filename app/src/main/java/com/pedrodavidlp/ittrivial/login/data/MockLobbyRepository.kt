package com.pedrodavidlp.ittrivial.login.data

import android.os.Handler
import android.os.Looper
import com.pedrodavidlp.ittrivial.base.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import kotlin.properties.Delegates

class MockLobbyRepository: LobbyRepository {
  override fun getGames(observer: Observer<List<Game>>): List<Game> {
    var listGames: List<Game> by Delegates.observable(emptyList()) { _, _, new ->
     observer.onChange(new)
    }
    Handler(Looper.getMainLooper()).postDelayed({
      listGames = listOf(Game("Franlo gay"))
    },500)
    Handler(Looper.getMainLooper()).postDelayed({
      listGames = listOf(Game("Esto no va a ir"),Game("Esto no va a ir"),Game("Esto no va a ir"))
    },1000)
    return listGames
  }

  override fun getUsersInGame(game: Game, observer: Observer<List<User>>): List<User> {
    var listUsers: List<User> by Delegates.observable(emptyList()){ _, _, new ->
      observer.onChange(new)
    }
    listUsers = listOf(User("franlo"))
    Handler(Looper.getMainLooper()).postDelayed({
      listUsers = listOf(User("franlo"), User("cotel"))
    }, 7000)
    Handler(Looper.getMainLooper()).postDelayed({
     listUsers = listOf(User("franlo"), User("cotel"), User("nhemesy"))
    }, 15000)

    return listUsers
  }

  override fun onInitGame(callback: UserListContract.InteractorOutput) {
    callback.onInitGame()
  }

  override fun joinGame(game: Game, callback: GameListContract.InteractorOutput) {
    //mock dont do nothing
  }
}