package com.pedrodavidlp.ittrivial.login.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pedrodavidlp.ittrivial.R


class RecyclerListener private constructor(private val mRecyclerView: RecyclerView) {
  private val mAttachListener = object : RecyclerView.OnChildAttachStateChangeListener {
    override fun onChildViewAttachedToWindow(view: View) {
      view.setOnClickListener(mOnClickListener)
    }

    override fun onChildViewDetachedFromWindow(view: View) {
    }
  }
  lateinit var mOnItemClickListener: OnItemClickListener

  private val mOnClickListener = View.OnClickListener { v ->
    val holder = mRecyclerView.getChildViewHolder(v)
    mOnItemClickListener.onItemClicked(mRecyclerView, holder.adapterPosition, v)
  }

  init {
    mRecyclerView.setTag(R.id.item_click_support, this)
    mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener)
  }

  fun setOnItemClickListener(listener: OnItemClickListener): RecyclerListener {
    mOnItemClickListener = listener
    return this
  }

  interface OnItemClickListener {

    fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View)
  }

  companion object {

    fun addTo(view: RecyclerView): RecyclerListener {
      var support: RecyclerListener? = view.getTag(R.id.item_click_support) as? RecyclerListener
      if (support == null) {
        support = RecyclerListener(view)
      }
      return support
    }

    fun removeFrom(view: RecyclerView): RecyclerListener {
      val support = view.getTag(R.id.item_click_support) as RecyclerListener

      return support
    }
  }
}