package com.pedrodavidlp.ittrivial.login

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.items_player_list.view.*

class PlayerListAdapter: RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder>(){
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder =
      PlayerListViewHolder(LayoutInflater.from(parent.context)
      .inflate(R.layout.items_player_list,parent,false))

  override fun getItemCount(): Int {
    return 5
  }

  override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
    holder.setData("Hola")
  }

  inner class PlayerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun setData(username: String) {
      itemView.itemUsername.text = username
    }

  }
}