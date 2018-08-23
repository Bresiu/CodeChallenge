package com.bresiu.codechallenge.model

import com.bresiu.codechallenge.data.entity.Address
import com.bresiu.codechallenge.data.entity.Company
import com.bresiu.codechallenge.data.entity.Geo
import com.bresiu.codechallenge.data.entity.User

class UserCombined(var users: List<User>, var addresses: List<Address>, var geos: List<Geo>,
                   var companies: List<Company>)
