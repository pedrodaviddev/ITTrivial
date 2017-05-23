package com.pedrodavidlp.ittrivial.login.domain.repository

import com.pedrodavidlp.ittrivial.base.Observer
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User

interface LobbyRepository {
  fun getGames(observer: Observer<List<Game>>): List<Game>
  fun getUsersInGame(game: Game, observer: Observer<List<User>>): List<User>
  fun onInitGame(callback: UserListContract.InteractorOutput)
  fun joinGame(game: Game, callback: GameListContract.InteractorOutput)
}