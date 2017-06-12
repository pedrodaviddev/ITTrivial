package com.pedrodavidlp.ittrivial.login.data

import com.pedrodavidlp.ittrivial.base.domain.data.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

class MockLobbyRepository : LobbyRepository {
  override fun getCurrentActivePlayer(game: Game, observer: Observer<Player?>): Player? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getUsersInGame(game: Game, observer: Observer<List<Player>>): List<Player> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getGames(observer: Observer<List<Game>>): List<Game> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

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
}