package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.Post

class PostModelToEntityMapper : Mapper.ObjectMapper<Post, com.bresiu.codechallenge.model.Post> {
  override fun map(mappingItem: com.bresiu.codechallenge.model.Post): Post {
    return Post(mappingItem.id.toLong(), mappingItem.userId.toLong(), mappingItem.title, mappingItem.body)
  }
}