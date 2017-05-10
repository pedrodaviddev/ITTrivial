package com.pedrodavidlp.ittrivial.login.domain.repository

import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.PlayerListContract

interface LobbyRepository {
  fun getGames(callback: GameListContract.InteractorOutput)
  fun getUsersInGame(callback: PlayerListContract.InteractorOutput)
  fun onInitGame(callback: PlayerListContract.InteractorOutput)
  fun joinGame(callback: GameListContract.InteractorOutput)
}