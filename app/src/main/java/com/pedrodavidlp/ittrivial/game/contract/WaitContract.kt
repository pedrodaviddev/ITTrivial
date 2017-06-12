package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player

class WaitContract {
  interface View {
    fun changeTurn(player: Player)
    fun myTurn()
    fun showListPlayers(listPlayer: List<Player>)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun getTurn()
  }

  interface Interactor {
    fun getTurn(game: Game, callback: WaitContract.InteractorOutput)
  }

  interface InteractorOutput {
    fun onMyTurn()
    fun onChangeTurn(player: Player)
    fun onLeaveGame()
    fun onGameEnds()
  }

  interface Router {
  }

}
