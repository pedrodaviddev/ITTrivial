package com.pedrodavidlp.ittrivial.login.domain

import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.PlayerListContract

interface GameRepository {
  fun getUsersInGame(callback: PlayerListContract.InteractorOutput)
  fun getGameList(callback: GameListContract.InteractorOutput)
}