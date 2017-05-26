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
    val randomnums = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 6, 5, 1, 6, 6, 6, 1, 3, 2)
    holder.setData(listGames[position].name, randomnums[position])
  }


  inner class GamesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var playersIcons =
        listOf(itemView.user1, itemView.user2,
            itemView.user3, itemView.user4,
            itemView.user5, itemView.user6)

    fun setData(username: String, playerCount: Int) {
      itemView.gameName.text = username
      if (playerCount > 2) {
        itemView.numberPlayersBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.mediumCapacity)
        itemView.gameNameBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.mediumCapacityAlpha)
      }

      if (playerCount > 4) {
        itemView.numberPlayersBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.littleCapacity)
        itemView.gameNameBackground
            .background = ContextCompat.getDrawable(itemView.context, R.color.littleCapacityAlpha)
      }
      (0..playerCount - 1)
          .forEach {
            playersIcons[it]
                .setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_user_blue))
          }

    }

  }
}