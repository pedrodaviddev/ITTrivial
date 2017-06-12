package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.base.pattern.Observer
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.Category

interface GameRepository {
  fun getTurnInGame(game: Game, observer: Observer<Player>): Player
  fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput)
  fun winCategory(game: Game, username: String, category: Category)
  fun leaveGame(player: Player, game: Game, callback: WaitContract.InteractorOutput)
  fun removeListeners(game: Game)
}