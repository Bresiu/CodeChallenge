package com.bresiu.codechallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.adapters.AlbumListAdapter.ViewHolder
import com.bresiu.codechallenge.model.AlbumListItem
import com.bresiu.codechallenge.presentation.views.HeaderItemDecoration.StickyHeaderInterface

class AlbumListAdapter : ListAdapter<AlbumListItem, ViewHolder>(
    AlbumListDiffCallback()), StickyHeaderInterface {
  override fun bindHeaderData(header: TextView, headerPosition: Int) {
    val txt: String = getItem(headerPosition).toString()
    header.text = txt
  }

  override fun getHeaderLayout(headerPosition: Int): Int {
    return R.layout.header_view
  }

  override fun getHeaderPositionForItem(itemPosition: Int): Int {
    var i = itemPosition
    while (true) {
      if (isHeader(i)) return i
      i--
    }
  }

  override fun isHeader(itemPosition: Int): Boolean {
    return getItem(itemPosition).isHeader()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.header_view, parent, false)
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.apply {
      bind(item)
      itemView.tag = item
    }
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view as TextView
    fun bind(item: AlbumListItem) {
      textView.text = item.toString()
    }
  }
}
