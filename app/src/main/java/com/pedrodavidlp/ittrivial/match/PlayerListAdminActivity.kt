package com.pedrodavidlp.ittrivial.match

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.activity_player_list_admin.*

class PlayerListAdminActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player_list_admin)
    adminPlayerList.adapter = PlayerListAdapter()
    adminPlayerList.layoutManager = LinearLayoutManager(applicationContext)
  }
}
