package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    foreignKeys = [ForeignKey(entity = Album::class, parentColumns = ["id"],
        childColumns = ["albumId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("albumId")]
)
class Photo(
    @PrimaryKey var id: Long,
    var albumId: Long,
    var title: String,
    var url: String,
    var thumbnailUrl: String
)