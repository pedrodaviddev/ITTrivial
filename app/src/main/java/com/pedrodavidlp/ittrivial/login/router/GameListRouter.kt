package com.pedrodavidlp.ittrivial.login.router

import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.view.GamesListActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListGuestActivity
import org.jetbrains.anko.startActivity

class GameListRouter(private val activity: GamesListActivity) : GameListContract.Router {
  override fun goToPlayerGameList() {
    activity.startActivity<PlayerListGuestActivity>()
  }

}