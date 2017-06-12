package com.pedrodavidlp.ittrivial.game.view.activity.transition

import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.view.View
import android.widget.ImageView
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.view.Category

class TransitionNetwork(image: ImageView, router: RouletteRouter) : TransitionTemplate(image, router) {
  override fun goToQuestion() {
    router.goToQuestion(Category.NETWORK)
  }

  override fun setImage() {
    image.setImageResource(R.drawable.ic_network)
  }

  override fun animate() {
    val animation = SpringAnimation(image, DynamicAnimation.ROTATION_Y)
    animation.setStartVelocity(0.00000000000001f)
    val force = SpringForce()
    force.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    force.stiffness = SpringForce.STIFFNESS_VERY_LOW
    animation.spring = force
    animation.addEndListener { _, _, _, _ ->
      image.visibility = View.GONE
      goToQuestion()
    }
    animation.animateToFinalPosition(360.0f)
  }

}