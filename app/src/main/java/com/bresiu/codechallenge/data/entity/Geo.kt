package com.bresiu.codechallenge.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "geos",
    foreignKeys = [ForeignKey(entity = Address::class, parentColumns = ["userId"],
        childColumns = ["addressId"], onDelete = ForeignKey.CASCADE)]
)
class Geo(
    @PrimaryKey var addressId: Int,
    var lat: Double,
    var lng: Double
)
