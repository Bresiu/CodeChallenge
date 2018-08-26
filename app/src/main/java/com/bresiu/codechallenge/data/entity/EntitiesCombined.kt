package com.bresiu.codechallenge.data.entity

import com.bresiu.codechallenge.model.UserCombined

class EntitiesCombined(
    var userCombined: UserCombined,
    var posts: List<Post>,
    var albums: List<Album>,
    var photos: List<Photo>
)
