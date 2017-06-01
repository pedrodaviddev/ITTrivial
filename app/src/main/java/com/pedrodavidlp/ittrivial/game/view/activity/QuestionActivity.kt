package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.data.MockQuestionRepository
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
import com.pedrodavidlp.ittrivial.game.view.Category
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*

class QuestionActivity : AppCompatActivity(), QuestionContract.View {
  lateinit private var presenter: QuestionPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_question)
    val cat = intent.getSerializableExtra("Category") as Category
    this.setCategoryUI(cat)
    presenter = QuestionPresenter(FireQuestionRepository(), QuestionRouter(this))
    presenter.getQuestion(cat)
    presenter.setView(this)
    presenter.init()
  }


  override fun showError(message: String) {

  }

  override fun onLoadQuestion(question: Question) {
    questionText.text = question.question
    val random = Random()
    val position = random.nextInt(3)+1
    if (position == 1) {
      first.text = question.answer
      second.text = question.wAns1
      third.text = question.wAns2
      fourth.text = question.wAns3

      first.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
        presenter.hit()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
    }
    if(position == 2) {
      first.text = question.wAns1
      second.text = question.answer
      third.text = question.wAns2
      fourth.text = question.wAns3

      first.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
        presenter.hit()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
    }
    if(position == 3){
      first.text = question.wAns1
      second.text = question.wAns2
      third.text = question.answer
      fourth.text = question.wAns3

      first.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
        presenter.hit()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
    }
    if(position == 4){
      first.text = question.wAns1
      second.text = question.wAns2
      third.text = question.wAns3
      fourth.text = question.answer

      first.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_red_dark))
        presenter.fail()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark))
        presenter.hit()
      }
    }
  }

  private fun setCategoryUI(category: Category) {
    when (category) {
      Category.HARDWARE -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.hardwareDark))
        title = "Hardware"
      }
      Category.ENTERPRISE -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.enterpriseDark))
        title = "Empresa"
      }
      Category.HISTORY -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.historyDark))
        title = "Historia"
      }
      Category.NETWORK -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.networkDark))
        title = "Redes"
      }
      Category.SOFTWARE -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.softwareDark))
        title = "Software"
      }
    }
  }

  private fun removeAllListeners() {
    first.setOnClickListener(null)
    second.setOnClickListener(null)
    third.setOnClickListener(null)
    fourth.setOnClickListener(null)
  }


}
