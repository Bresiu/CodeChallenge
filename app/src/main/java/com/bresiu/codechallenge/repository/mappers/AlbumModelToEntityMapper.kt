package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.Album

class AlbumModelToEntityMapper : Mapper.ObjectMapper<Album, com.bresiu.codechallenge.model.Album> {

  override fun map(mappingItem: com.bresiu.codechallenge.model.Album): Album {
    return Album(mappingItem.id, mappingItem.userId, mappingItem.title)
  }
}
