package com.pedrodavidlp.ittrivial.login.view

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import kotlinx.android.synthetic.main.items_games_list.view.*

class GamesListAdapter : RecyclerView.Adapter<GamesListAdapter.GamesListViewHolder>() {
  var listGames: List<Game> = ArrayList()
    get() = field
    set(list) {
      field = list
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListViewHolder =
      GamesListViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.items_games_list, parent, false))

  override fun getItemCount(): Int {
    return listGames.size
  }

  override fun onBindViewHolder(holder: GamesListViewHolder, position: Int) {
    holder.setData(listGames[position].name, position + 1)
  }


  inner class GamesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(username: String, playerCount: Int) {
      itemView.gameName.text = username
      itemView.numberPlayers.text = "$playerCount jugadores"
      if (playerCount > 4) {
        itemView.numberPlayersBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.mediumCapacity)
        itemView.gameNameBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.mediumCapacityAlpha)
      }

      if (playerCount > 7) {
        itemView.numberPlayersBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.littleCapacity)
        itemView.gameNameBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.littleCapacityAlpha)
      }

    }

  }
}