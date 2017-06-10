package com.pedrodavidlp.ittrivial.login.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.Category

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder>() {

  var listPlayers: List<Player> = ArrayList()
    get() = field
    set(list){
      field = list
      notifyDataSetChanged()
    }

  override fun onBindViewHolder(holder: PlayerListViewHolder?, position: Int) {
    holder.setData
  }

  override fun getItemCount(): Int {
    return listPlayers.size
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
    PlayerListViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.items_player_list, parent, false))
  }


  inner class PlayerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var categoryIcons =
        listOf(itemView.cat1, itemView.cat2,
               itemView.cat3, itemView.cat4, itemView.cat5)

    fun setData(username: String, categories: List<Category>){
      itemView.playerName.text = username

    }
  }
}