package com.pedrodavidlp.ittrivial.game.view

import android.content.Context

import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
class Roulette(rContext: Context, attrs: AttributeSet) : ImageView(rContext, attrs) {
  private var current: String = ""
  lateinit private var rChangeListener: RouletteChangeListener

  init {
    this.setOnTouchListener(RouletteTouchListener())
  }

  fun setWheelChangeListener(rouletteChangeListener: RouletteChangeListener) {
    this.rChangeListener = rouletteChangeListener
  }

  private fun rotateWheel() {
    val auxAngle = generateRotationAnge()
    val animation: Animation = RotateAnimation(0.0f, auxAngle + 1440.0f,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f)

    animation.repeatCount = 0
    animation.interpolator = AccelerateDecelerateInterpolator()
    animation.duration = 3000

    setQuadrant(((rotation % 360) + auxAngle) % 360)
    super.startAnimation(animation)
    animation.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationRepeat(p0: Animation?) {

      }

      override fun onAnimationEnd(p0: Animation?) {
        rChangeListener.onSelectionChange(current)
        rotation += auxAngle
        this@Roulette.setOnTouchListener(RouletteTouchListener())
      }

      override fun onAnimationStart(p0: Animation?) {

      }

    })

  }

  private fun setQuadrant(angle: Float) {
    current = if (angle < 72)  "Hardware"
    else if (angle < 144) "Network"
    else if (angle < 216)  "Software"
    else if (angle < 288)  "Enterprise"
    else "History"
  }

  private fun generateRotationAnge(): Float {
    when ((Math.random() * 5).toInt()) {
      0 -> return 72.0f
      1 -> return 144.0f
      2 -> return 216.0f
      3 -> return 288.0f
    }
    return 360.0f
  }

  interface RouletteChangeListener {
    fun onSelectionChange(selectedPosition: String)
  }

  private inner class RouletteTouchListener : OnTouchListener {
    override fun onTouch(v: View, event: MotionEvent): Boolean {
      rotateWheel()
      this@Roulette.setOnTouchListener(null)
      return true
    }
  }

  fun getCurrentPos(): String {
    return current
  }
}

