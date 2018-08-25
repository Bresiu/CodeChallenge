package com.bresiu.codechallenge.model

data class AlbumItem(
    override val id: Long,
    val title: String
) : AlbumListItem {
  override fun isHeader(): Boolean {
    return true
  }

  override fun toString() = title
}
