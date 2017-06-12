package com.pedrodavidlp.ittrivial.game.view.activity.transition

import android.widget.ImageView
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category

class TransitionEnterprise(image: ImageView, router: GameRouter) : TransitionTemplate(image, router) {
  override fun goToQuestion() {
    router.goToQuestion(Category.ENTERPRISE)
  }

  override fun setImage() {
    image.setImageResource(R.drawable.ic_enterprise)
  }
}