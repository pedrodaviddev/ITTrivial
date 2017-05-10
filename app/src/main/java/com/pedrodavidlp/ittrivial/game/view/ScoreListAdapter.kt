package com.pedrodavidlp.ittrivial.login.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.domain.model.User
import kotlinx.android.synthetic.main.items_scores_list.view.*

class ScoreListAdapter : RecyclerView.Adapter<ScoreListAdapter.ScoreListViewHolder>() {
  private var listPlayers: List<Player> = ArrayList()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListViewHolder =
      ScoreListViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.items_scores_list, parent, false))

  override fun getItemCount(): Int {
    return listPlayers.size
  }

  override fun onBindViewHolder(holder: ScoreListViewHolder, position: Int) {
    holder.setData(listPlayers[position].username)
  }

  fun setList(list: List<Player>) {
    this.listPlayers = list
    notifyDataSetChanged()
  }

  inner class ScoreListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(username: String) {
      itemView.usernameInScore.text = username
    }

  }
}