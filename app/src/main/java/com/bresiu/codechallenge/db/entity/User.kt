package com.bresiu.codechallenge.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class User {
    @PrimaryKey
    var id: Long = 0
    var name: String? = null
    var username: String? = null
    var email: String? = null
    var address: Address? = null
    internal var phone: String? = null
    internal var website: String? = null
    internal var company: Company? = null

    inner class Address {
        internal var street: String? = null
        internal var suite: String? = null
        internal var city: String? = null
        internal var zipcode: String? = null
        internal var geo: Geo? = null

        inner class Geo {
            internal var lat: Double = 0.toDouble()
            internal var lng: Double = 0.toDouble()
        }
    }

    inner class Company {
        internal var name: String? = null
        internal var catchPhrase: String? = null
        internal var bs: String? = null
    }
}
