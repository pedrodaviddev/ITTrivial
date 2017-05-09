package com.pedrodavidlp.ittrivial.login.domain

import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.PlayerListContract

interface GameRepository {
  fun getUsersInGame(game: Game, callback: PlayerListContract.InteractorOutput)
  fun getGameList(callback: GameListContract.InteractorOutput)
  fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput)
  fun loseTurnInGame(game: Game)
}