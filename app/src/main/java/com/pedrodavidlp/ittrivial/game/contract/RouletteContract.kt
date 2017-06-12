package com.pedrodavidlp.ittrivial.game.contract

import com.pedrodavidlp.ittrivial.game.view.Category

class RouletteContract {
  interface View {
    fun initUi()
    fun initMedals(hw: Boolean, sw: Boolean, net: Boolean, entrpr: Boolean, hstry: Boolean)
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
