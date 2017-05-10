package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.view.GameActivity
import kotlinx.android.synthetic.main.activity_player_list_admin.*
import org.jetbrains.anko.startActivity

class PlayerListAdminActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player_list_admin)
    adminPlayerList.adapter = UserListAdapter()
    adminPlayerList.layoutManager = LinearLayoutManager(applicationContext)
    startGameButton.setOnClickListener {
      startActivity<GameActivity>()
    }
  }
}
