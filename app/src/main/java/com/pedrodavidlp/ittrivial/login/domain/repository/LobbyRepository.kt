package com.pedrodavidlp.ittrivial.login.domain.repository

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User

interface LobbyRepository {
  fun getGames(callback: GameListContract.InteractorOutput)
  fun getUsersInGame(game: Game, callback: UserListContract.InteractorOutput)
  fun onInitGame(game: Game, callback: UserListContract.InteractorOutput)
  fun createGame(admin: User, callback: MenuContract.InteractorOutput)
  fun enterGame(game: Game, callback: GameListContract.InteractorOutput)
  fun exitGame(game: Game, callback: UserListContract.InteractorOutput)
}