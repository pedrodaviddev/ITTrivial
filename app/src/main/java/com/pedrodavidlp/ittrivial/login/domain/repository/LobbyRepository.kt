package com.pedrodavidlp.ittrivial.login.domain.repository

import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract

interface LobbyRepository {
  fun getGames(callback: GameListContract.InteractorOutput)
  fun getUsersInGame(callback: UserListContract.InteractorOutput)
  fun onInitGame(callback: UserListContract.InteractorOutput)
  fun joinGame(callback: GameListContract.InteractorOutput)
}