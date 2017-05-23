package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.usecase.CreateGame
import com.pedrodavidlp.ittrivial.login.router.MenuRouter

class MenuPresenter(private val router: MenuRouter, private val usecase: CreateGame): MenuContract.Presenter {
  lateinit var vw: MenuContract.View

  override fun init(){
    this.setWelcome()
  }
  override fun setView(view: MenuContract.View) {
    this.vw = view
  }

  private fun setWelcome(){
    vw.setWelcome(Session.username)
  }

  fun searchGame() {
    router.searchGames()
  }

  fun createGame() {
    usecase.createGame(User(Session.username))
    router.createGame()
  }
}