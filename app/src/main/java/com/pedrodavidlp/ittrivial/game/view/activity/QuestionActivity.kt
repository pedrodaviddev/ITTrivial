package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
import com.pedrodavidlp.ittrivial.game.view.Category
import kotlinx.android.synthetic.main.activity_question.*
import org.jetbrains.anko.alert
import java.util.*

class QuestionActivity : AppCompatActivity(), QuestionContract.View {
  lateinit private var presenter: QuestionPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_question)
    val category = intent.getSerializableExtra("Category") as Category
    presenter = QuestionPresenter(FireQuestionRepository(), FireGameRepository(), QuestionRouter(this))
    presenter.setView(this)
    presenter.init(category)
  }


  override fun showError(message: String) {

  }

  override fun onLoadQuestion(question: Question) {
    questionText.text = question.question
    val random = Random()
    val position = random.nextInt(3) + 1
    if (position == 1) {
      first.text = question.answer
      second.text = question.wans1
      third.text = question.wans2
      fourth.text = question.wans3

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
    if (position == 2) {
      first.text = question.wans1
      second.text = question.answer
      third.text = question.wans2
      fourth.text = question.wans3

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
    if (position == 3) {
      first.text = question.wans1
      second.text = question.wans2
      third.text = question.answer
      fourth.text = question.wans3

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
    if (position == 4) {
      first.text = question.wans1
      second.text = question.wans2
      third.text = question.wans3
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

  override fun onBackPressed() {
    alert("Are you sure to leave the game?"){
      title("Exit")
      yesButton {
        //TODO
      }
      noButton {}
    }.show()
  }

}
