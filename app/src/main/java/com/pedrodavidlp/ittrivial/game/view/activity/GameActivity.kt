package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter

class GameActivity : AppCompatActivity(), GameContract.View {
  lateinit private var presenter: GamePresenter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter = ServiceLocator.provideGamePresenter(this)
    presenter.setView(this)
    presenter.init()
  }

  override fun initUI() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
