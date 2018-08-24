package com.bresiu.codechallenge.data.entity

import androidx.room.Relation
import com.bresiu.codechallenge.data.entity.Photo

data class AlbumWithPhotos(
    val id: Long,
    val title: String,
    @Relation(parentColumn = "id", entityColumn = "albumId")
    val photos: List<Photo> = arrayListOf()
)
