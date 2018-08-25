package com.bresiu.codechallenge.data.entity

import androidx.room.Relation

class AlbumWithPhotos {
  var id: Long = 0
  var title: String = ""
  @Relation(parentColumn = "id", entityColumn = "albumId")
  var photos: List<Photo> = arrayListOf()
}