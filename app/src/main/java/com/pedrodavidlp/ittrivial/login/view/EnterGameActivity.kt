package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.login.contract.EnterGameContract
import com.pedrodavidlp.ittrivial.login.presenter.EnterGamePresenter
import kotlinx.android.synthetic.main.activity_enter_game.*

class EnterGameActivity : AppCompatActivity(), EnterGameContract.View {
  lateinit var presenter: EnterGamePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_enter_game)
    presenter = ServiceLocator.provideEnterGamePresenter(this)
    presenter.setView(this)
    playButton.setOnClickListener {
      presenter.pickUsername(username.text.toString())
    }
  }

  override fun showError(message: String) {
    usernameLayout.error = message
  }


}
