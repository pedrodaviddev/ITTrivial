package com.pedrodavidlp.ittrivial.game.contract

class MenuContract {
  interface View {
    fun setWelcome(username: String)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
  }

  interface Router {
    fun createGame()
    fun searchGames()
  }

}
