package com.bresiu.codechallenge.adapters

import androidx.recyclerview.widget.DiffUtil
import com.bresiu.codechallenge.data.entity.AlbumWithPhotos

class AlbumListDiffCallback : DiffUtil.ItemCallback<AlbumWithPhotos>() {
  override fun areItemsTheSame(oldItem: AlbumWithPhotos, newItem: AlbumWithPhotos): Boolean {
    return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.photos.containsAll(newItem.photos)
  }

  override fun areContentsTheSame(oldItem: AlbumWithPhotos, newItem: AlbumWithPhotos): Boolean {
    return oldItem == newItem
  }
}