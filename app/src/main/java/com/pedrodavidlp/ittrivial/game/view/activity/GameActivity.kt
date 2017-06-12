package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import org.jetbrains.anko.alert

class GameActivity : AppCompatActivity(), GameContract.View {
  lateinit private var presenter: GamePresenter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val turn = intent.getBooleanExtra("turn", false)
    presenter = ServiceLocator.provideGamePresenter(this)
    presenter.setView(this)
    presenter.init(turn)
  }

  override fun initUI() {

  }


  override fun onBackPressed() {
    alert("Are you sure to leave the game?") {
      title("Exit")
      yesButton {
        finish()
      }
      noButton {}
    }.show()
  }
}
