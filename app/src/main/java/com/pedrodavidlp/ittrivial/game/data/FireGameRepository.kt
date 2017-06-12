package com.pedrodavidlp.ittrivial.game.data

import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.base.domain.data.Observer
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.view.Category
import java.util.HashMap
import kotlin.collections.ArrayList
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class FireGameRepository : GameRepository {
  val database: FirebaseDatabase = FirebaseDatabase.getInstance()
  val ref: DatabaseReference = database.reference
  lateinit var listener: ValueEventListener

  override fun getTurnInGame(game: Game, observer: Observer<Player>): Player {
    var playerPlaying: Player by Delegates.observable(Player()) {
      _: KProperty<*>, old: Player, new: Player ->
      observer.onValueChange(new, old)
    }


    ref.child("games").child(game.name).addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {}

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        if (dataSnapshot.child("started").getValue(Boolean::class.java)) {
          val turn = dataSnapshot.child("turn").getValue(Int::class.java)
          val userList = ArrayList<Player>()
          val playerMap: HashMap<*, *> = dataSnapshot.child("players").value as HashMap<*, *>
          playerMap.entries.forEach {
            val map = dataSnapshot.value as HashMap<*, *>
            val player: Player =
                Player(it.key.toString(),
                    map["admin"].toString() == "true",
                    map["history"].toString() == "true",
                    map["hardware"].toString() == "true",
                    map["network"].toString() == "true",
                    map["software"].toString() == "true",
                    map["enterprise"].toString() == "true"
                )
            userList.add(player)
          }
          playerPlaying = userList[turn]
        }
      }

    })
    return playerPlaying
    /*listener = object : ValueEventListener {
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val userList = ArrayList<Player>()
        val playerMap: HashMap<*, *> = dataSnapshot.child("players").value as HashMap<*, *>
        playerMap.entries.forEach {
          val map = dataSnapshot.value as HashMap<*, *>
          val player: Player =
              Player(it.key.toString(),
                  map["admin"].toString() == "true",
                  map["history"].toString() == "true",
                  map["hardware"].toString() == "true",
                  map["network"].toString() == "true",
                  map["software"].toString() == "true",
                  map["enterprise"].toString() == "true"
              )
          userList.add(player)
        }
        val turn = dataSnapshot.child("turn").getValue(Int::class.java)
        if (userList[turn].player == Session.player) {
          this@FireGameRepository.removeListeners(game)
          callback.onMyTurn()
        } else {
          callback.onChangeTurn(userList[turn])
        }
      }

      override fun onCancelled(p0: DatabaseError?) {

      }

    }
    ref.child("games").child(game.name).addValueEventListener(listener)*/
  }

  override fun loseTurnInGame(game: Game, callback: QuestionContract.InteractorOutput) {
    ref.child("games").child(game.name).addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val numberPlayers = dataSnapshot.child("players").childrenCount.toInt()
        val turn = dataSnapshot.child("turn").getValue(Int::class.java)
        val nextTurn = this@FireGameRepository.selectNextTurn(numberPlayers, turn)
        ref.child("games").child(game.name).child("turn").setValue(nextTurn)
        callback.loseTurn()
      }

      override fun onCancelled(p0: DatabaseError?) {}
    })
  }

  override fun winCategory(game: Game, username: String, category: Category) {
    ref.child("games").child(game.name).child("players")
        .child(username).child(category.name.toLowerCase()).setValue(true)
  }

  override fun leaveGame(player: Player, game: Game, callback: WaitContract.InteractorOutput) {
    ref.child("games").child(game.name).addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {

      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val userList = ArrayList<Player>()
        val playerMap: HashMap<*, *> = dataSnapshot.child("players").value as HashMap<*, *>
        playerMap.entries.forEach {
          val map = dataSnapshot.value as HashMap<*, *>
          val player: Player =
              Player(it.key.toString(),
                  map["admin"].toString() == "true",
                  map["history"].toString() == "true",
                  map["hardware"].toString() == "true",
                  map["network"].toString() == "true",
                  map["software"].toString() == "true",
                  map["enterprise"].toString() == "true"
              )
          userList.add(player)
        }
        val turn = dataSnapshot.child("turn").getValue(Int::class.java)
        if (userList.indexOf(player) < turn) {
          ref.child("games").child(game.name).child("turn").setValue(turn - 1)
        }
        ref.child("games").child(game.name).child("players").child(player.username).removeValue()
        callback.onLeaveGame()
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

  override fun removeListeners(game: Game) {
    ref.child("games").child(game.name).removeEventListener(listener)
  }
}
