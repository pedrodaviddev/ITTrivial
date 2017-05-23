package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.CreateGame
import com.pedrodavidlp.ittrivial.login.presenter.MenuPresenter
import com.pedrodavidlp.ittrivial.login.router.MenuRouter
import com.pedrodavidlp.ittrivial.login.view.GamesListActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdminActivity
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity

class MenuActivity : AppCompatActivity(), MenuContract.View {
  lateinit var presenter: MenuPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    presenter = MenuPresenter(MenuRouter(this), CreateGame(FireLobbyRepository()))
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
}
