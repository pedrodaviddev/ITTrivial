package com.pedrodavidlp.ittrivial.game.view.activity.transition

import android.os.Handler
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.view.View
import android.widget.ImageView
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category

class TransitionNetwork(image: ImageView, router: GameRouter) : TransitionTemplate(image, router) {
  override fun goToQuestion() {
    Handler().postDelayed({
      router.goToQuestion(Category.NETWORK)
    }, 700)
  }

  override fun setImage() {
    image.setImageResource(R.drawable.ic_network)
  }

  override fun animate() {
    val animation = SpringAnimation(image, DynamicAnimation.ROTATION_Y)
    val force = SpringForce()
    force.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    force.stiffness = SpringForce.STIFFNESS_LOW
    animation.spring = force
    animation.addEndListener { _, _, _, _ ->
      goToQuestion()
      Handler().postDelayed({
        image.visibility = View.GONE
      }, 2000)
    }
    animation.animateToFinalPosition(360.0f)
  }

}