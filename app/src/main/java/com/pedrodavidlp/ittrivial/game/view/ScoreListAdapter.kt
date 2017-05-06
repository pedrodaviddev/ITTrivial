package com.pedrodavidlp.ittrivial.login.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.items_scores_list.view.*

class ScoreListAdapter : RecyclerView.Adapter<ScoreListAdapter.ScoreListViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListViewHolder =
      ScoreListViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.items_scores_list, parent, false))

  override fun getItemCount(): Int {
    return 5
  }

  override fun onBindViewHolder(holder: ScoreListViewHolder, position: Int) {
    holder.setData("Hola", 30 + position)
  }

  inner class ScoreListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(username: String, score: Int) {
      itemView.usernameInScore.text = username
      itemView.scorePlayer.text = score.toString()
    }

  }
}