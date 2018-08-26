package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey var id: Long,
    var name: String,
    var username: String,
    var email: String,
    var phone: String,
    var website: String
)
