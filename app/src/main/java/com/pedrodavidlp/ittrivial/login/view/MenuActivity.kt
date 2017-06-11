package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.presenter.MenuPresenter
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), MenuContract.View {

  lateinit var presenter: MenuPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    presenter = ServiceLocator.provideMenuPresenter(this)
    presenter.setView(this)
    presenter.init()


    createMatchButton.setOnClickListener {
      presenter.createGame()
    }
    joinMatchButton.setOnClickListener {
      presenter.searchGame()
    }

  }

  override fun setWelcome(username: String) {
    welcomeText.text = "Bienvenido $username"
  }

  override fun showError(message: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
