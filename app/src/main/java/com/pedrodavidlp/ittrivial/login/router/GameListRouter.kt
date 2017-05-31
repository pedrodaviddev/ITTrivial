package com.pedrodavidlp.ittrivial.login.router

import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.view.GameListActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListGuestActivity
import org.jetbrains.anko.startActivity

class GameListRouter(private val activity: GameListActivity) : GameListContract.Router {
  override fun goToPlayerGameList() {
    activity.startActivity<PlayerListGuestActivity>()
    activity.finish()
  }

}