package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.login.view.MenuActivity
import kotlinx.android.synthetic.main.activity_enter_game.*
import org.jetbrains.anko.startActivity

class EnterGameActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_enter_game)
    playButton.setOnClickListener {
      startActivity<MenuActivity>()
      finish()
    }
  }

}
