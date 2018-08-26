package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("userId")],
    tableName = "posts",
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"],
        childColumns = ["userId"], onDelete = ForeignKey.CASCADE)]
)
class Post(
    @PrimaryKey var id: Long,
    var userId: Long,
    var title: String,
    var body: String
)
