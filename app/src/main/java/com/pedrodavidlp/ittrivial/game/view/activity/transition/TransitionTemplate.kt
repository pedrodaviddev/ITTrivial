package com.pedrodavidlp.ittrivial.game.view.activity.transition

import android.view.View
import android.view.View.VISIBLE
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import com.pedrodavidlp.ittrivial.game.router.GameRouter

abstract class TransitionTemplate(val image: ImageView, val router: GameRouter) {

  fun makeTransition() {
    setImage()
    image.visibility = VISIBLE
    animate()
  }

  protected abstract fun goToQuestion()

  protected abstract fun setImage()

  protected open fun animate() {
    val animation = AlphaAnimation(0.0f, 1.0f)
    animation.duration = 2000
    animation.setAnimationListener(object : Animation.AnimationListener{
      override fun onAnimationRepeat(p0: Animation?) {}

      override fun onAnimationEnd(p0: Animation?) {
        goToQuestion()
        image.visibility = View.GONE
      }

      override fun onAnimationStart(p0: Animation?) {}

    })
    image.startAnimation(animation)
  }
}