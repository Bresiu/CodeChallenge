package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.Photo

class PhotoModelToEntityMapper : Mapper.ObjectMapper<Photo, com.bresiu.codechallenge.model.Photo> {

  override fun map(mappingItem: com.bresiu.codechallenge.model.Photo): Photo {
    return Photo(mappingItem.id.toLong(), mappingItem.albumId.toLong(), mappingItem.title, mappingItem.url,
        mappingItem.thumbnailUrl)
  }
}