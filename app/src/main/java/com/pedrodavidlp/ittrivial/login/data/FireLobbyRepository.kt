package com.pedrodavidlp.ittrivial.login.data

import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.contract.UserListContract
import com.pedrodavidlp.ittrivial.login.domain.model.User
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import java.util.*

class FireLobbyRepository: LobbyRepository {
  val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()
  val ref: DatabaseReference = firebase.reference

  override fun createGame(game: Game, admin: Player) {
    ref.child("games").child(game.name).child("players").child("admin").setValue(admin)
    ref.child("games").child(game.name).child("numplayers").setValue(1)
    ref.child("games").child(game.name).child(game.name).child("started").setValue(0)
  }

  override fun getGames(callback: GameListContract.InteractorOutput) {
    ref.child("games").addValueEventListener(object :ValueEventListener{
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
    ref.child("games").child(game.name).child("players").addValueEventListener(object : ValueEventListener{
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
    ref.child("games").child(game.name).child("numplayers").addValueEventListener(object : ValueEventListener{
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val random = Random()
        val numPlayers = dataSnapshot.getValue(Int:: class.java)
        val num = random.nextInt(numPlayers - 1) + 1
        ref.child("games").child(game.name).child("started").setValue(1)
        ref.child("games").child(game.name).child("turn").setValue(num)
      }
    })
  }

  override fun joinGame(game: Game, callback: GameListContract.InteractorOutput) {
    ref.child("games").child(game.name).child("numplayers").addValueEventListener(object : ValueEventListener{
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val numPlayers = dataSnapshot.getValue(Int :: class.java)
        ref.child("games").child(game.name).child("numplayers").setValue(numPlayers + 1)
        ref.child("games").child(game.name).child("players").child("pl$numPlayers").setValue(Player("pl$numPlayers"))
      }
    })
  }
}