package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.login.view.PlayerListGuestActivity
import kotlinx.android.synthetic.main.activity_games_list.*
import org.jetbrains.anko.startActivity

class GamesListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_games_list)
    gamesList.adapter = GamesListAdapter()
    gamesList.layoutManager = LinearLayoutManager(applicationContext)
    gamesList.setOnClickListener {
      startActivity<PlayerListGuestActivity>()
    }
  }
}
