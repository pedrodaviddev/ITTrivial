package com.pedrodavidlp.ittrivial.login.router

import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.game.view.GameActivity
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import org.jetbrains.anko.startActivity

class UserListRouter(private val activity: AppCompatActivity) : UserListContract.Router {
  override fun goToGameActivity() {
    activity.startActivity<GameActivity>()
    activity.finish()
  }
}