package com.pedrodavidlp.ittrivial.login.router

import com.pedrodavidlp.ittrivial.login.view.EnterGameActivity
import com.pedrodavidlp.ittrivial.login.view.MenuActivity
import org.jetbrains.anko.startActivity

class EnterGameRouter(private val activity: EnterGameActivity) {
  fun goToMenu() {
    activity.startActivity<MenuActivity>()
    activity.finish()
  }
}