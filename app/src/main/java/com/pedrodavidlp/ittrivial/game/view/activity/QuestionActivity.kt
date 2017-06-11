package com.pedrodavidlp.ittrivial.game.view.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
import com.pedrodavidlp.ittrivial.game.view.Category
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import kotlin.concurrent.timerTask

class QuestionActivity : AppCompatActivity(), QuestionContract.View {

  lateinit private var presenter: QuestionPresenter
  private val timer = Timer()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_question)
    val category = intent.getSerializableExtra("Category") as Category
    timeIndicator.max = 200
    setCategoryUI(category)
    presenter = ServiceLocator.provideQuestionPresenter(this)
    presenter.setView(this)
    presenter.init(category)
  }


  override fun showError(message: String) {

  }

  override fun onLoadQuestion(question: Question) {
    startCountdown()
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

  private fun startCountdown() {
    timeIndicator.progress = 200

    timer.scheduleAtFixedRate(timerTask {
      if (timeIndicator.progress > 0) {
        timeIndicator.progress = timeIndicator.progress - 1
      } else {
        this@QuestionActivity.presenter.fail()
      }
    }, 0, 50)
  }

  private fun setCategoryUI(category: Category) {
    when (category) {
      Category.HARDWARE -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.hardwareDark))
        title = "Hardware"
        timeIndicator.progressDrawable.setColorFilter(ContextCompat.getColor(applicationContext, R.color.hardware),
            PorterDuff.Mode.SRC_IN)
      }
      Category.ENTERPRISE -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.enterpriseDark))
        title = "Empresa"
        timeIndicator.progressDrawable.setColorFilter(ContextCompat.getColor(applicationContext, R.color.enterprise),
            PorterDuff.Mode.SRC_IN)
      }
      Category.HISTORY -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.historyDark))
        title = "Historia"
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(applicationContext, R.color.history),
            PorterDuff.Mode.SRC_IN)
      }
      Category.NETWORK -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.networkDark))
        title = "Redes"
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(applicationContext, R.color.network),
            PorterDuff.Mode.SRC_IN)
      }
      Category.SOFTWARE -> {
        container.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.softwareDark))
        title = "Software"
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(applicationContext, R.color.software),
            PorterDuff.Mode.SRC_IN)
      }
    }

  }

  override fun stopCounter() {
    timer.cancel()
  }

  private fun removeAllListeners() {
    first.setOnClickListener(null)
    second.setOnClickListener(null)
    third.setOnClickListener(null)
    fourth.setOnClickListener(null)
  }


}
