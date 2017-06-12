package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.Category

class RouletteContract {
  interface View {
    fun initUi()
    fun loadList(playerList: List<Player>)
    fun finishedGame()
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
  }

  interface InteractorOutput {
  }

  interface Router {
    fun goToQuestion(category: Category)
    fun goToMenu()
    fun goToWait()
  }
}
