package com.pedrodavidlp.ittrivial.login

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.items_games_list.view.*

class GamesListAdapter : RecyclerView.Adapter<GamesListAdapter.GamesListViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListViewHolder =
      GamesListViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.items_games_list, parent, false))

  override fun getItemCount(): Int {
    return 5
  }

  override fun onBindViewHolder(holder: GamesListViewHolder, position: Int) {
    holder.setData("Partida $position")
  }

  inner class GamesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(username: String) {
      itemView.gameName.text = username
    }

  }
}