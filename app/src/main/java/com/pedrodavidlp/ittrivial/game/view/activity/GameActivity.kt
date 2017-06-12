package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.base.pattern.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import org.jetbrains.anko.contentView

class GameActivity : AppCompatActivity(), GameContract.View {
  companion object {
    lateinit var instance: GameActivity
  }
  lateinit private var presenter: GamePresenter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val turn = intent.getBooleanExtra("turn", false)
    presenter = ServiceLocator.Game.Presenter.provideGame(this)
    presenter.setView(this)
    instance = this
    presenter.init(turn)
  }

  override fun initUI() {

  }


  override fun onBackPressed() {
    Snackbar
        .make(contentView!!, "No puedes abandonar una partida mientras esta en marcha", Snackbar.LENGTH_SHORT)
        .show()
  }
}
