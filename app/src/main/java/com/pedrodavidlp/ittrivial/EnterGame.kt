package com.pedrodavidlp.ittrivial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EnterGame : AppCompatActivity() {

    lateinit private var playButton: Button
    lateinit private var username: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_game)
        playButton = findViewById(R.id.playButton) as Button
        username = findViewById(R.id.username) as EditText

    }

}
