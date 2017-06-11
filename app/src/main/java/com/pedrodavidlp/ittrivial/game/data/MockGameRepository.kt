package com.pedrodavidlp.ittrivial.game.data

import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository

class MockGameRepository : GameRepository {
  override fun leaveGame(player: Player, game: Game, callback: WaitContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput) {

  }

  override fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput) {

  }
}