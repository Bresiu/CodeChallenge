package com.bresiu.codechallenge.model

interface AlbumListItem {
  val id: Long
  fun isHeader(): Boolean
}