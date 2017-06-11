package com.pedrodavidlp.ittrivial.game.domain.model

data class Player(val username: String, val history: Boolean,
                  val hardware: Boolean, val network: Boolean,
                  val software: Boolean, val enterprise: Boolean){

  constructor(username: String): this(username, false, false, false, false, false)
  constructor() : this("")

  override fun equals(other: Any?): Boolean {
    return this::class == other!!::class &&
        this.username == (other as Player).username
  }

  fun isTheSame(player: Player): Boolean {
    return this.username == player.username
  }

  fun isTheSame(username: String): Boolean {
    return this.username == username
  }
}