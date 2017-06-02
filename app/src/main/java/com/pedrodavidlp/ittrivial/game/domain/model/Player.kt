package com.pedrodavidlp.ittrivial.game.domain.model

data class Player(val username: String, val history: Boolean,
                  val hardware: Boolean, val network: Boolean,
                  val software: Boolean, val enterprise: Boolean){

  constructor(username: String): this(username, false, false, false, false, false)
  constructor(): this("")
}