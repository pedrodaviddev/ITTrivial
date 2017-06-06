package com.pedrodavidlp.ittrivial.login.router

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import org.jetbrains.anko.startActivity

class UserListRouter(private val activity: AppCompatActivity) : UserListContract.Router {
  override fun goToGame(myTurn: Boolean) {
    Log.d("AQUI PASA ALGO EXTRAÑO", myTurn.toString())
    activity.startActivity<GameActivity>(Pair("a", myTurn))
    activity.finish()
  }
}