package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.view.GameActivity
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList
import com.pedrodavidlp.ittrivial.login.presenter.UserListPresenter
import com.pedrodavidlp.ittrivial.login.router.UserListRouter
import kotlinx.android.synthetic.main.activity_player_list_admin.*
import org.jetbrains.anko.startActivity

class PlayerListAdminActivity : AppCompatActivity(), UserListContract.View {
  override fun initUI() {

  }

  lateinit private var presenter: UserListPresenter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player_list_admin)
    adminPlayerList.adapter = UserListAdapter()
    adminPlayerList.layoutManager = LinearLayoutManager(applicationContext)
    startGameButton.setOnClickListener {
      startActivity<GameActivity>()
      finish()
    }
    presenter = UserListPresenter(GetUserList(FireLobbyRepository()), UserListRouter(this))
    presenter.setView(this)
    presenter.init()
  }

  override fun onLoadList(list: List<User>) {
    (adminPlayerList.adapter as UserListAdapter).setList(list)
  }

  override fun showError(message: String) {

  }
}
