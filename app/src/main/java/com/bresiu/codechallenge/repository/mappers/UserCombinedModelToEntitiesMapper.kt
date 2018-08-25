package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.Address
import com.bresiu.codechallenge.data.entity.Company
import com.bresiu.codechallenge.data.entity.Geo
import com.bresiu.codechallenge.model.User
import com.bresiu.codechallenge.model.UserCombined
import java.util.*

class UserCombinedModelToEntitiesMapper : Mapper.ObjectMapper<UserCombined, List<User>> {
  override fun map(mappingItem: List<User>): UserCombined {
    val userEntities = ArrayList<com.bresiu.codechallenge.data.entity.User>(mappingItem.size)
    val addressEntities = ArrayList<com.bresiu.codechallenge.data.entity.Address>(mappingItem.size)
    val geoEntities = ArrayList<com.bresiu.codechallenge.data.entity.Geo>(mappingItem.size)
    val companyEntities = ArrayList<com.bresiu.codechallenge.data.entity.Company>(mappingItem.size)
    for (user in mappingItem) {
      userEntities.add(
          com.bresiu.codechallenge.data.entity.User(user.id.toLong(), user.name, user.username,
              user.email, user.phone, user.website))
      addressEntities.add(Address(user.id, user.address.street, user.address.suite, user.address.city, user.address.zipcode))
      geoEntities.add(Geo(user.id, user.address.geo.lat, user.address.geo.lng))
      companyEntities.add(
          Company(user.id, user.company.name, user.company.catchPhrase, user.company.bs))
    }
    return UserCombined(userEntities, addressEntities, geoEntities, companyEntities)
  }
}
