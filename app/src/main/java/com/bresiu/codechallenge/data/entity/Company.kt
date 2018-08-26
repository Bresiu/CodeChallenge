package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "companys",
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"],
        childColumns = ["userId"], onDelete = ForeignKey.CASCADE)]
)
class Company(
    @PrimaryKey var userId: Int,
    var name: String,
    var catchPhrase: String,
    var bs: String
)
