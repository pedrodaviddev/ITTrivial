package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.presenter.RoulettePresenter
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.Roulette
import kotlinx.android.synthetic.main.fragment_roulette.*

class RouletteFragment : Fragment(), RouletteContract.View, Roulette.OnCategorySelected {

  lateinit var presenter: RoulettePresenter

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    presenter = ServiceLocator.provideRoulettePresenter(this)
    return inflater?.inflate(R.layout.fragment_roulette, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity.title = "¡Toca para girar la ruleta!"
    presenter.setView(this)
    presenter.init()
  }

  override fun initUi() {
    roulette.setWheelChangeListener(object : Roulette.RouletteChangeListener {
      override fun onSelectionChange(category: Category) {
        selectedCategoryText.visibility = VISIBLE
        selectedCategoryText.text = getCategoryName(category)
        selectedCategoryText.setTextColor(getCategoryColor(category))
      }
    })
    roulette.setOnCategorySelectedListener(this)
  }


  override fun onCategorySelected(category: Category) {
    presenter.goToQuestion(category, transitionImage)
  }

  override fun initMedals(hw: Boolean, sw: Boolean, net: Boolean, entrpr: Boolean, hstry: Boolean) {
    if (hw) {
      hardware.setImageResource(R.drawable.ic_hardware)
    } else {
      hardware.setImageResource(R.drawable.ic_hardware_gray)
    }
    if (sw) {
      software.setImageResource(R.drawable.ic_software)
    } else {
      software.setImageResource(R.drawable.ic_software_gray)
    }
    if (net) {
      network.setImageResource(R.drawable.ic_network)
    } else {
      network.setImageResource(R.drawable.ic_network_gray)
    }
    if (entrpr) {
      enterprise.setImageResource(R.drawable.ic_enterprise)
    } else {
      enterprise.setImageResource(R.drawable.ic_enterprise_gray)
    }
    if (hstry) {
      history.setImageResource(R.drawable.ic_history)
    } else {
      history.setImageResource(R.drawable.ic_history_gray)
    }
  }

  private fun getCategoryColor(category: Category): Int {
    when (category) {
      Category.HISTORY -> return ContextCompat.getColor(context, R.color.history)
      Category.HARDWARE -> return ContextCompat.getColor(context, R.color.hardware)
      Category.SOFTWARE -> return ContextCompat.getColor(context, R.color.software)
      Category.ENTERPRISE -> return ContextCompat.getColor(context, R.color.enterprise)
      Category.NETWORK -> return ContextCompat.getColor(context, R.color.network)
    }
  }

  private fun getCategoryName(category: Category): String {
    when (category) {
      Category.HISTORY -> return "Historia"
      Category.HARDWARE -> return "Hardware"
      Category.SOFTWARE -> return "Software"
      Category.ENTERPRISE -> return "Empresas"
      Category.NETWORK -> return "Redes"
    }
  }
}
