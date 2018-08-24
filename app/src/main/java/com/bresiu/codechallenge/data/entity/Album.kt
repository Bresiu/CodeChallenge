package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "albums",
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"],
        childColumns = ["userId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("userId")]
)
data class Album(
    @PrimaryKey val id: Long,
    val userId: Long,
    val title: String
)