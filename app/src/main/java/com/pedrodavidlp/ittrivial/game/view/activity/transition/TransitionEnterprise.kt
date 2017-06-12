package com.pedrodavidlp.ittrivial.game.view.activity.transition

import com.pedrodavidlp.ittrivial.game.view.Category

class TransitionEnterprise(image: android.widget.ImageView, router: com.pedrodavidlp.ittrivial.game.router.RouletteRouter) : TransitionTemplate(image, router) {
  override fun goToQuestion() {
    router.goToQuestion(Category.ENTERPRISE)
  }

  override fun setImage() {
    image.setImageResource(com.pedrodavidlp.ittrivial.R.drawable.ic_enterprise)
  }
}