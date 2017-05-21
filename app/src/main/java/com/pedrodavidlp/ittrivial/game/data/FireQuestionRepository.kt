package com.pedrodavidlp.ittrivial.game.data

import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository
import java.util.*

class FireQuestionRepository: QuestionRepository {
  val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()
  val ref : DatabaseReference = firebase.reference
  val questionList = ArrayList<Question>()

  override fun getQuestion(category: String, callback: QuestionContract.InteractorOutput) {  // To change: category is a Category, not a String.
    ref.child("questions").child(category).addValueEventListener(object : ValueEventListener{
      override fun onCancelled(p0: DatabaseError?) {
        callback.onError()
      }

      override fun onDataChange(dataSnapshot: DataSnapshot) {
        val random = Random()
        val numQuestions = dataSnapshot.childrenCount.toInt()
        var num = random.nextInt(numQuestions - 1)+1
        var question = dataSnapshot.child("q$num").getValue(Question::class.java)
        while (question in questionList) {
          num = random.nextInt(numQuestions - 1)+1
          question = dataSnapshot.child("q$num").getValue(Question::class.java)
        }
        questionList.add(question as Question)
        callback.onQuestionLoaded(question)
        }
    })
  }

  override fun updateCrowns(game: Game, category: String, player: Player) { //Same as before
    ref.child("games").child(game.name).child("players").child(player.username).child(category).setValue(1)
  }
}