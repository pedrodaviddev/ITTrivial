package com.pedrodavidlp.ittrivial.game.view.activity.transition

import com.pedrodavidlp.ittrivial.game.view.Category

class TransitionHardware(image: android.widget.ImageView, router: com.pedrodavidlp.ittrivial.game.router.GameRouter): TransitionTemplate(image, router) {
  override fun goToQuestion() {
    router.goToQuestion(Category.HARDWARE)
  }

  override fun setImage() {
    image.setImageResource(com.pedrodavidlp.ittrivial.R.drawable.ic_hardware)
  }
}