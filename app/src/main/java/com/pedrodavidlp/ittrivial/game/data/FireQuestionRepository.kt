package com.pedrodavidlp.ittrivial.game.data

import com.google.firebase.database.*
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.QuestionRepository

class FireQuestionRepository: QuestionRepository {
  val database: FirebaseDatabase = FirebaseDatabase.getInstance()
  val ref: DatabaseReference = database.getReference("questions")

  override fun loadQuestion(callback: QuestionContract.InteractorOutput) {
    ref.addValueEventListener(object: ValueEventListener{
      override fun onDataChange(dataSnapShot: DataSnapshot) {
        var q1: DataSnapshot = dataSnapShot.child("enterprise").child("q1").getValue(String::class.java)

        var question: String = q1.child("question").getValue(String::class.java)
        var answer: String   = q1.child("answer").getValue(String::class.java)
        var wAns1: String    = q1.child("wans1").getValue(String::class.java)
        var wAns2: String    = q1.child("wans2").getValue(String::class.java)
        var wAns3: String    = q1.child("wans3").getValue(String::class.java)
      }

      override fun onCancelled(error: DatabaseError?) {
        callback.onError()
      }

    })
  }


}
