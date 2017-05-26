package com.pedrodavidlp.ittrivial.login.data

import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import java.util.*
import kotlin.collections.ArrayList

class FireLobbyRepository : LobbyRepository {
  val randomNames: Array<String> = arrayOf("Ailurophile", "Assemblage",
      "Becoming", "Beleaguer", "Brood",
      "Bucolic", "Bungalow", "Chatoyant", "Comely",
      "Conflate", "Cynosure", "Dalliance", "Demesne",
      "Dominion", "Demure", "Denouement", "Desuetude",
      "Desultory", "Diaphanous", "Dissemble", "Dulcet",
      "Ebullience", " Effervescent", "Efflorescence",
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


  override fun createGame(admin: User) {
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
        while(keys.contains(name)){
          name = selectRandomName()
        }
        ref.child("games").child(name).child("players").child("admin").setValue(admin)
        ref.child("games").child(name).child("numplayers").setValue(1)
        ref.child("games").child(name).child("started").setValue(0)
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
          gameList.add(Game(it.key))
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
        val userList = ArrayList<User>()
        dataSnapshot.children.forEach {
          userList.add(User(it.key))
        }
        callback.onFetchUserListSuccess(userList)
      }
    })
  }

  override fun onInitGame(game: Game, callback: UserListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("numplayers").addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val random = Random()
        val numPlayers = dataSnapshot.getValue(Int::class.java)
        val num = random.nextInt(numPlayers - 1) + 1
        ref.child("games").child(game.name).child("started").setValue(1)
        ref.child("games").child(game.name).child("turn").setValue(num)
      }
    })
  }

  override fun joinGame(game: Game, callback: GameListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("numplayers").addValueEventListener(object : ValueEventListener {
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val numPlayers = dataSnapshot.getValue(Int::class.java)
        ref.child("games").child(game.name).child("numplayers").setValue(numPlayers + 1)
        ref.child("games").child(game.name).child("players").child("pl$numPlayers").setValue(Player("pl$numPlayers"))
      }
    })
  }

  private fun selectRandomName(): String {
    return randomNames[Random().nextInt(randomNames.size - 1)]
  }

  override fun enterGame(game: Game, callback: GameListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("players").child(Session.username).setValue(Player(Session.username))
    callback.onJoinGame(game)
  }
}