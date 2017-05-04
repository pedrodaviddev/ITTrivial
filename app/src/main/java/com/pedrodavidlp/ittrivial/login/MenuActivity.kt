package com.pedrodavidlp.ittrivial.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.join.GamesListActivity
import com.pedrodavidlp.ittrivial.join.PlayerListAdminActivity
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity

class MenuActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    createMatchButton.setOnClickListener {
      startActivity<PlayerListAdminActivity>()
      finish()
    }
    joinMatchButton.setOnClickListener {
      startActivity<GamesListActivity>()
      finish()
    }

  }
}
