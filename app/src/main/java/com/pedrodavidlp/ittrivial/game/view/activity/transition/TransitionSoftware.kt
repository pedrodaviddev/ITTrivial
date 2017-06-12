package com.pedrodavidlp.ittrivial.game.view.activity.transition

import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category

class TransitionSoftware(image: ImageView, router: GameRouter) : TransitionTemplate(image, router) {
  override fun goToQuestion() {
    router.goToQuestion(Category.SOFTWARE)
  }

  override fun setImage() {
    image.setImageResource(R.drawable.ic_software)
  }

  override fun animate() {
    image.visibility = View.VISIBLE
    val animation = AlphaAnimation(0.1f, 1.0f)
    animation.repeatCount = 6
    animation.duration = 320
    image.startAnimation(animation)
    animation.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationRepeat(animation: Animation?) {}

      override fun onAnimationEnd(animation: Animation?) {
        goToQuestion()
        Handler().postDelayed({
          image.visibility = View.GONE
        }, 2000)
      }

      override fun onAnimationStart(animation: Animation?) {}
    })
  }
}