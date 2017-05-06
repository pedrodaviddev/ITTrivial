package com.pedrodavidlp.ittrivial.login.data

import com.pedrodavidlp.ittrivial.game.domain.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.PlayerListContract
import com.pedrodavidlp.ittrivial.login.domain.GameRepository
import com.pedrodavidlp.ittrivial.login.domain.model.Player

class MockGameRepository: GameRepository {

  fun playerGenerator(quantity: Int): MutableList<Player>{
    var recipients = mutableListOf<Player>()
    var player: Player?
    for(i in 1..quantity){
      recipients.add(Player("Player $quantity",0))
    }
    return recipients
  }

  fun gameGenerator(quantity: Int): MutableList<Game>{
    var recipients = mutableListOf<Game>()
    var player: Player?
    for(i in 1..quantity){
      recipients.add(Game("Game $quantity"))
    }
    return recipients
  }


  override fun getUsersInGame(callback: PlayerListContract.InteractorOutput) {
    callback.onGetSuccess(playerGenerator(4))
  }

  override fun getGameList(callback: GameListContract.InteractorOutput) {
    callback.onFetchGameListSuccess(gameGenerator(4))
  }

}