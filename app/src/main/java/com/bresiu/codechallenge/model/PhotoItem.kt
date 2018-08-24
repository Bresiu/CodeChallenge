package com.bresiu.codechallenge.model

data class PhotoItem(
    override val id: Long,
    val url: String,
    val thumbnailUrl: String
) : AlbumListItem {
  override fun isHeader(): Boolean {
    return false
  }

  override fun toString() = url
}
