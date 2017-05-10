package com.pedrodavidlp.ittrivial.login.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.domain.model.User
import kotlinx.android.synthetic.main.items_games_list.view.*

class GamesListAdapter : RecyclerView.Adapter<GamesListAdapter.GamesListViewHolder>() {
  private var listGames: List<Game> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListViewHolder =
      GamesListViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.items_games_list, parent, false))

  override fun getItemCount(): Int {
    return listGames.size
  }

  override fun onBindViewHolder(holder: GamesListViewHolder, position: Int) {
    holder.setData(listGames[position].name)
  }

  fun setList(list: List<Game>) {
    this.listGames = list
    notifyDataSetChanged()
  }

  inner class GamesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(username: String) {
      itemView.gameName.text = username
    }

  }
}