package com.pedrodavidlp.ittrivial.login.data

import android.util.Log
import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.MenuContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import java.util.*
import kotlin.collections.ArrayList

class FireLobbyRepository : LobbyRepository {
  companion object {
    val playerNumber: Array<String> = arrayOf("I", "II", "III", "IV", "V", "VI")
  }
  val randomNames: Array<String> = arrayOf("Ailurophile", "Assemblage",
      "Becoming", "Beleaguer", "Brood", "Bucolic", "Bungalow", "Chatoyant", "Comely",
      "Conflate", "Cynosure", "Dalliance", "Demesne", "Dominion", "Demure", "Denouement", "Desuetude",
      "Desultory", "Diaphanous", "Dissemble", "Dulcet", "Ebullience", " Effervescent", "Efflorescence",
      "Elision", "Elixir", "Eloquence", "Embrocation", "Emollient", "Ephemeral",
      "Epiphany", "Erstwhile", "Ethereal", "Evanescent", "Evocative", "Fetching",
      "Felicity", "Forbearance", "Fugacious", "Furtive", "Gambol", "Glamour",
      "Gossamer", "Halcyon", "Harbinger", "Imbrication", "Imbroglio", "Imbue",
      "Incipient", "Ineffable", "Ingénue", "Inglenook", "Insouciance", "Inure",
      "Labyrinthine", "Lagniappe", "Lagoon", "Languor", "Lassitude", "Leisure",
      "Lilt", "Lissome", "Lithe", "Love", "Mellifluous", "Moiety", "Mondegreen",
      "Murmurous", "Nemesis", "Offing", "Onomatopoeia", "Opulent", "Palimpsest",
      "Panacea", "Panoply", "Pastiche", "Penumbra", "Petrichor", "Plethora", "Propinquity",
      "Pyrrhic", "Quintessential", "Ratatouille", "Ravel", "Redolent", "Riparian",
      "Ripple", "Scintilla", "Sempiternal", "Seraglio", "Serendipity", "Summery",
      "Sumptuous", "Surreptitious", "Susquehanna", "Susurrous", "Talisman", "Tintinnabulation",
      "Umbrella", "Untoward", "Vestigial", "Wafture", "Wherewithal", "Woebegone")

  val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()
  val ref: DatabaseReference = firebase.reference

  override fun createGame(admin: Player, callback: MenuContract.InteractorOutput) {
    ref.child("games").addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(databaseError: DatabaseError) {
        TODO(databaseError.message)
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val keys = ArrayList<String>()
        dataSnapshot.children.forEach {
          keys.add(it.key)
        }
        var name = selectRandomName()
        while (keys.contains(name)) {
          name = selectRandomName()
        }
        ref.child("games").child(name).child("players").child(playerNumber[0]).setValue(admin)
        ref.child("games").child(name).child("numplayers").setValue(1)
        ref.child("games").child(name).child("started").setValue(false)
        callback.onGameCreated(Game(name, 1, false))
      }

    })

  }


  override fun getGames(callback: GameListContract.InteractorOutput) {
    ref.child("games").addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val gameList = ArrayList<Game>()
        dataSnapshot.children.forEach {
          gameList.add(
              Game(it.key,
                  it.child("players").childrenCount.toInt(),
                  it.child("started").getValue(Boolean::class.java)))
        }
        callback.onFetchGameListSuccess(gameList)
      }
    })
  }

  override fun getUsersInGame(game: Game, callback: UserListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("players").addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val userList = ArrayList<Player>()
        (0..dataSnapshot.childrenCount.toInt() - 1).forEach {
          val dsnap = dataSnapshot.child(playerNumber[it])
          userList.add(dsnap.getValue(Player::class.java))
        }
        Log.d("Mi nombre de user", "${Session.username} y el otro ${userList[0].username}")
        if (userList[0].username != Session.username) {
          this@FireLobbyRepository.setListenerToStartGame(game, callback)
        }
        callback.onFetchUserListSuccess(userList)
      }
    })
  }

  private fun setListenerToStartGame(game: Game, callback: UserListContract.InteractorOutput) {
    ref.child("games").child(game.name).addListenerForSingleValueEvent(object : ValueEventListener {

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        if (dataSnapshot.child("started").getValue(Boolean::class.java)) {
          val turn = dataSnapshot.child("players").childrenCount
          ref.child("games").child(game.name).removeEventListener(this)
          if (isMyTurn(dataSnapshot, turn.toInt())) {
            callback.onInitAndMyTurn()
          } else {
            Log.d("APAREZCO AQUI?", "UG")
            callback.onInitAndWait()
          }
        } else {
          ref.child("games").child(game.name).addListenerForSingleValueEvent(this)
        }
      }

      override fun onCancelled(p0: DatabaseError?) {
      }

    })
  }

  private fun isMyTurn(dataSnapshot: DataSnapshot, turn: Int): Boolean {
    return dataSnapshot.child("players")
        .child(playerNumber[turn])
        .child("nickname")
        .getValue(String::class.java) == Session.username
  }


  override fun startGame(game: Game, callback: UserListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("players")
        .addListenerForSingleValueEvent(object : ValueEventListener {
          override fun onDataChange(dataSnapshot: DataSnapshot) {
            val randomTurn = this@FireLobbyRepository
                .selectRandomTurn(dataSnapshot.childrenCount.toInt())
            ref.child("games")
                .child(game.name).child("turn")
                .setValue(randomTurn)
            ref.child("games").child(game.name).child("started").setValue(true)
            if (randomTurn == 0)
              callback.onInitAndMyTurn()
            else
              callback.onInitAndWait()

          }

          override fun onCancelled(p0: DatabaseError?) {
            callback.onError()
          }
        })
  }

  private fun selectRandomTurn(numberPlayers: Int): Int {
    return Random().nextInt(numberPlayers)
  }

  private fun selectRandomName(): String {
    return randomNames[Random().nextInt(randomNames.size - 1)]
  }

  override fun enterGame(game: Game, callback: GameListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("players").addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val numPlayers = dataSnapshot.childrenCount.toInt()
        ref.child("games").child(game.name).child("players")
            .child(playerNumber[numPlayers]).setValue(Player(Session.username))
        callback.onJoinGame(game)
      }
    })
  }

  override fun exitGame(game: Game, callback: UserListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("players").child(Session.username).removeValue()
  }
}
