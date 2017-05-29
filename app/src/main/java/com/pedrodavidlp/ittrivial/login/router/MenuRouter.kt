package com.pedrodavidlp.ittrivial.login.router

import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.view.GameListActivity
import com.pedrodavidlp.ittrivial.login.view.MenuActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdminActivity
import org.jetbrains.anko.startActivity

class MenuRouter(private val activity: MenuActivity) : MenuContract.Router {
  override fun createGame() {
    activity.startActivity<PlayerListAdminActivity>()
  }

  override fun searchGames() {
    activity.startActivity<GameListActivity>()
  }
}