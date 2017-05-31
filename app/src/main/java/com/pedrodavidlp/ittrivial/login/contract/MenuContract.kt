package com.pedrodavidlp.ittrivial.login.contract

import com.pedrodavidlp.ittrivial.game.domain.model.Game

class MenuContract {
  interface View {
    fun setWelcome(username: String)

    fun showError(message: String)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)

  }

  interface Router {
    fun createGame()
    fun searchGames()
  }

  interface InteractorOutput {
    fun onGameCreated(game: Game)
  }

}