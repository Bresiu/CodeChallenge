package com.bresiu.codechallenge.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(entity = Album::class, parentColumns = arrayOf("id"), childColumns = arrayOf("albumId"), onDelete = ForeignKey.CASCADE))])
class Photo {
    @PrimaryKey
    internal var id: Long = 0
    internal var albumId: Long = 0
    internal var title: String? = null
    internal var url: String? = null
    internal var thumbnailUrl: String? = null
}
