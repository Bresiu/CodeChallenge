package com.bresiu.codechallenge.adapters

import androidx.recyclerview.widget.DiffUtil
import com.bresiu.codechallenge.model.AlbumListItem

class AlbumListDiffCallback : DiffUtil.ItemCallback<AlbumListItem>() {
  override fun areItemsTheSame(oldItem: AlbumListItem, newItem: AlbumListItem): Boolean {
    return oldItem.isHeader() == newItem.isHeader() && oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: AlbumListItem, newItem: AlbumListItem): Boolean {
    return oldItem == newItem
  }
}