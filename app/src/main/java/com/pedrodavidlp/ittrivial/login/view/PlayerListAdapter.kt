package com.pedrodavidlp.ittrivial.login.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import kotlinx.android.synthetic.main.items_player_list.view.*

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder>() {

  var listPlayers: List<Player> = ArrayList()
    get() = field
    set(list) {
      field = list
      notifyDataSetChanged()
    }

  override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
    holder.setData(listPlayers[position])
  }

  override fun getItemCount(): Int {
    return listPlayers.size
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder =
      PlayerListViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.items_player_list, parent, false))


  inner class PlayerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(player: Player) {
      itemView.username.text = player.username
      if (player.history) {
        itemView.historyMedal.setImageResource(R.drawable.ic_history_gray)
      } else {
        itemView.historyMedal.setImageResource(R.drawable.ic_history)
      }
      if (player.enterprise) {
        itemView.enterpriseMedal.setImageResource(R.drawable.ic_enterprise_gray)
      } else {
        itemView.enterpriseMedal.setImageResource(R.drawable.ic_enterprise)
      }
      if (player.network) {
        itemView.networkMedal.setImageResource(R.drawable.ic_network_gray)
      } else {
        itemView.networkMedal.setImageResource(R.drawable.ic_network)
      }
      if (player.hardware) {
        itemView.hardwareMedal.setImageResource(R.drawable.ic_hardware_gray)
      } else {
        itemView.hardwareMedal.setImageResource(R.drawable.ic_hardware)
      }
      if (player.software) {
        itemView.softwareMedal.setImageResource(R.drawable.ic_software_gray)
      } else {
        itemView.softwareMedal.setImageResource(R.drawable.ic_software)
      }
    }
  }
}