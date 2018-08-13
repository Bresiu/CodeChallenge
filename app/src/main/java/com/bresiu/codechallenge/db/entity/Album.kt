package com.bresiu.codechallenge.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = ForeignKey.CASCADE))])
class Album {
    @PrimaryKey
    internal var id: Long = 0
    internal var userId: Long = 0
    internal var title: String? = null
}
