package com.pedrodavidlp.ittrivial.game.data

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.view.Category

class MockGameRepository : GameRepository {
  override fun listenEndGame(callback: WaitContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun winCategory(game: Game, username: String, category: Category) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun leaveGame(player: Player, game: Game, callback: WaitContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getPlayersOnGame(game: Game, callback: RouletteContract.InteractorOutput) {

  }

  override fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput) {

  }
}