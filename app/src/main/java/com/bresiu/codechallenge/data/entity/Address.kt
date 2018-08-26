package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "addresses",
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)]
)
class Address(
    @PrimaryKey var userId: Int,
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String
)
