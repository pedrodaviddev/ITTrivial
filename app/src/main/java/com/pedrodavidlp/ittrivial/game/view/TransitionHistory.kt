package com.pedrodavidlp.ittrivial.game.view

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category.HISTORY

class TransitionHistory(image: ImageView, router: GameRouter) : TransitionTemplate(image, router) {

  override fun setImage() {
    image.setImageResource(R.drawable.ic_history)
  }

  override fun animate() {
    image.visibility = VISIBLE
    val animation = RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f)
    animation.duration = 2000
    image.startAnimation(animation)
    animation.setAnimationListener(object : Animation.AnimationListener{
      override fun onAnimationRepeat(animation: Animation?) {}

      override fun onAnimationEnd(animation: Animation?) {
        goToQuestion()
        Handler().postDelayed({
          image.visibility = GONE
        }, 2000)
      }

      override fun onAnimationStart(animation: Animation?) {}
    })
  }

  override fun goToQuestion() {
    router.goToQuestion(HISTORY)
  }

}