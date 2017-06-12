package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.CreateGame
import com.pedrodavidlp.ittrivial.login.router.MenuRouter

class MenuPresenter(private val useCase: CreateGame, private val router: MenuRouter) :
    MenuContract.Presenter,
    MenuContract.InteractorOutput {


  lateinit var vw: MenuContract.View

  override fun init() {
    this.setWelcome()
  }

  override fun setView(view: MenuContract.View) {
    this.vw = view
  }


  override fun onGameCreated(game: Game) {
    Session.game = game
    router.createGame()
  }

  fun searchGame() {
    router.searchGames()
  }

  fun createGame() {
    useCase.createGame(Player(Session.player.username, true), this)
  }

  private fun setWelcome() {
    vw.setWelcome(Session.player.username)
  }
}