package com.pedrodavidlp.ittrivial.game.data

import android.util.Log
import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository

class FireGameRepository : GameRepository {

  val database: FirebaseDatabase = FirebaseDatabase.getInstance()
  val ref: DatabaseReference = database.reference
  override fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput) {

  }

  override fun getTurnInGame(game: Game, callback: WaitContract.InteractorOutput) {
    ref.child("games").child(game.name).addValueEventListener(object : ValueEventListener {
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val turn = dataSnapshot.child("turn").getValue(Int::class.java)
        val player = dataSnapshot.child("players").child(FireLobbyRepository.playerNumber[turn])
            .getValue(Player::class.java)
        if (player.username == Session.username) {
          callback.onMyTurn()
        } else {
          callback.onChangeTurn(player)
        }
      }

      override fun onCancelled(p0: DatabaseError?) {

      }

    })
  }

  override fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput) {
    ref.child("games").child(game.name).addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val numberPlayers = dataSnapshot.child("players").childrenCount.toInt()
        val turn = dataSnapshot.child("turn").getValue(Int::class.java)
        val nextTurn = this@FireGameRepository.selectNextTurn(numberPlayers, turn)

        Log.d("numero jugadores:", "$numberPlayers turno $turn siguiente $nextTurn ")
        ref.child("games").child(game.name).child("turn").setValue(nextTurn)
        callback.loseTurn()
      }

      override fun onCancelled(p0: DatabaseError?) {

      }


    })
  }

  override fun leaveGame(player: Player, game: Game, callback: WaitContract.InteractorOutput) {
    ref.child("games").child(game.name).child("players").addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(databaseError: DatabaseError) {}

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val playerLeaving: Int = dataSnapshot.children.map {
          it.getValue(Player::class.java)
        }.indexOf(player)
        ref.child("games").child(game.name).child("players")
            .child(FireLobbyRepository.playerNumber[playerLeaving])
            .removeValue()

      }

    })
  }

  private fun selectNextTurn(numberPlayers: Int, turn: Int): Int {
    if (turn < numberPlayers - 1) {
      return turn + 1
    } else {
      return 0
    }
  }

}
