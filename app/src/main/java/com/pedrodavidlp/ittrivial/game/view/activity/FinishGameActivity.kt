package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.activity_finish_game.*

class FinishGameActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_finish_game)
    val winnername = intent.getStringExtra("winner")
    winner.text = "¡$winnername ha ganado la partida! ¡¡Enhorabuena!!"
  }
}
