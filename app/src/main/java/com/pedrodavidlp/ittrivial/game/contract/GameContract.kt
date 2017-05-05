package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.login.domain.model.Player

class GameContract {
  interface View {
    fun initUi()
    fun finishedGame()
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun getScores()
  }

  interface Interactor {
    fun getScores()
  }

  interface InteractorOutput {
    fun onGetScores(playerList: List<Player>)
  }

  interface Router {
    fun goToQuestion()
    fun goToMenu()
  }
}