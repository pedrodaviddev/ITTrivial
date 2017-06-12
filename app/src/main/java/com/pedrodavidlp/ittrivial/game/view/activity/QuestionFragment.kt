package com.pedrodavidlp.ittrivial.game.view.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.base.pattern.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Question
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.view.Category
import kotlinx.android.synthetic.main.fragment_question.*
import java.util.*
import kotlin.concurrent.timerTask

class QuestionFragment(private val category: Category) : Fragment(), QuestionContract.View {

  lateinit private var presenter: QuestionPresenter
  private val timer = Timer()


  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    presenter = ServiceLocator.Game.Presenter.provideQuestion()
    return inflater?.inflate(R.layout.fragment_question, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.setView(this)
    setCategoryUI(category)
    timeIndicator.max = 200
    presenter.init(category)
  }


  override fun showError(message: String) {

  }

  override fun onLoadQuestion(question: Question) {
    questionLoader.visibility = View.GONE
    questionText.visibility = View.VISIBLE
    first.visibility = View.VISIBLE
    second.visibility = View.VISIBLE
    third.visibility = View.VISIBLE
    fourth.visibility = View.VISIBLE
    timeIndicator.visibility = View.VISIBLE

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
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.hit(category)
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        first.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        first.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        first.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
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
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        second.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.hit(category)
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        second.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        second.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
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
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        third.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        third.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.hit(category)
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        third.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
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
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        fourth.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      second.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        fourth.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      third.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        fourth.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.fail()
      }
      fourth.setOnClickListener {
        removeAllListeners()
        it.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
        presenter.hit(category)
      }
    }
  }

  private fun startCountdown() {
    timeIndicator.progress = 200

    timer.scheduleAtFixedRate(timerTask {
      if (timeIndicator.progress > 0) {
        timeIndicator.progress = timeIndicator.progress - 1
      } else {
        this@QuestionFragment.presenter.fail()
      }
    }, 0, 50)
  }

  private fun setCategoryUI(category: Category) {
    when (category) {
      Category.HARDWARE -> {
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.hardwareDark))
        questionLoader.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.hardware),
            PorterDuff.Mode.SRC_IN)
        activity.title = "Hardware"
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.hardware),
            PorterDuff.Mode.SRC_IN)
      }
      Category.ENTERPRISE -> {
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.enterpriseDark))
        activity.title = "Empresa"
        questionLoader.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.enterprise),
            PorterDuff.Mode.SRC_IN)
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.enterprise),
            PorterDuff.Mode.SRC_IN)
      }
      Category.HISTORY -> {
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.historyDark))
        activity.title = "Historia"
        questionLoader.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.history),
            PorterDuff.Mode.SRC_IN)
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.history),
            PorterDuff.Mode.SRC_IN)
      }
      Category.NETWORK -> {
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.networkDark))
        activity.title = "Redes"
        questionLoader.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.network),
            PorterDuff.Mode.SRC_IN)
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.network),
            PorterDuff.Mode.SRC_IN)
      }
      Category.SOFTWARE -> {
        container.setBackgroundColor(ContextCompat.getColor(context, R.color.softwareDark))
        activity.title = "Software"
        questionLoader.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.software),
            PorterDuff.Mode.SRC_IN)
        timeIndicator.progressDrawable.setColorFilter(
            ContextCompat.getColor(context, R.color.software),
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
