package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.Post

class PostModelToEntityMapper : Mapper.ObjectMapper<Post, com.bresiu.codechallenge.model.Post> {
    override fun map(`object`: com.bresiu.codechallenge.model.Post): Post {
        return Post(`object`.id.toLong(), `object`.userId.toLong(), `object`.title, `object`.body)
    }
}