package com.pedrodavidlp.ittrivial.login.domain.repository

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract

interface LobbyRepository {
  fun getGames(callback: GameListContract.InteractorOutput)
  fun getUsersInGame(game: Game, callback: UserListContract.InteractorOutput)
  fun onInitGame(game: Game, callback: UserListContract.InteractorOutput)
  fun joinGame(game: Game, callback: GameListContract.InteractorOutput)
  fun createGame(game: Game, admin: Player)
}