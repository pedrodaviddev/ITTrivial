package com.pedrodavidlp.ittrivial.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.activity_enter_game.*
import org.jetbrains.anko.startActivity

class EnterGame : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_enter_game)
    playButton.setOnClickListener {
      startActivity<MenuActivity>()
      finish()
    }
  }

}
