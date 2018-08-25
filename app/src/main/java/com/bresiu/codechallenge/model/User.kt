package com.bresiu.codechallenge.model

class User(var id: Int, var name: String, var username: String, var email: String, var address: Address, var phone: String,
           var website: String, var company: Company) {
  inner class Address(var street: String, var suite: String, var city: String, var zipcode: String, var geo: Geo)
  inner class Geo(var lat: Double, var lng: Double)
  inner class Company(var name: String, var catchPhrase: String, var bs: String)
}
