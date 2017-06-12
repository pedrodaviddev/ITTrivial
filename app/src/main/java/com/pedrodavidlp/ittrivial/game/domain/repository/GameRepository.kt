package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.Category

interface GameRepository {
  fun getPlayersOnGame(game: Game, callback: RouletteContract.InteractorOutput)
  fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput)
  fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput)
  fun winCategory(game: Game, username: String, category: Category)
  fun leaveGame(player: Player, game: Game, callback: WaitContract.InteractorOutput)
  fun listenEndGame(callback: WaitContract.InteractorOutput)
}