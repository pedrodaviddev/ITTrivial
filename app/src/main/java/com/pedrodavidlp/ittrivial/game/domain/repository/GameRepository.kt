package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game

interface GameRepository {
  fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput)
  fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput)
  fun loseTurnInGame(game: Game)
}