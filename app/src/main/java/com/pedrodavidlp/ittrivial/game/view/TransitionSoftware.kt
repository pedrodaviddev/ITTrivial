package com.pedrodavidlp.ittrivial.game.view

import android.widget.ImageView
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.router.GameRouter

class TransitionSoftware(image: ImageView, router: GameRouter): TransitionTemplate(image, router) {
  override fun goToQuestion() {

  }

  override fun setImage() {
    image.setImageResource(R.drawable.ic_software)
  }
}