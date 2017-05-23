package com.pedrodavidlp.ittrivial.login.router

import com.pedrodavidlp.ittrivial.game.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.view.MenuActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdminActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListGuestActivity
import org.jetbrains.anko.startActivity

class MenuRouter(private val activity: MenuActivity) : MenuContract.Router {
  override fun createGame() {
    activity.startActivity<PlayerListAdminActivity>()
  }

  override fun searchGames() {
    activity.startActivity<PlayerListGuestActivity>()
  }
}