package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.AlbumWithPhotos
import com.bresiu.codechallenge.model.AlbumItem
import com.bresiu.codechallenge.model.AlbumListItem
import com.bresiu.codechallenge.model.PhotoItem
import java.util.function.Consumer

class AlbumWithPhotosToAlbumListItemMapper : Mapper.ObjectMapper<List<AlbumListItem>, AlbumWithPhotos> {
  override fun map(mappingItem: AlbumWithPhotos): List<AlbumListItem> {
    val albumList: ArrayList<AlbumListItem> = arrayListOf()
    albumList.add(AlbumItem(mappingItem.id, mappingItem.title))
    mappingItem.photos.forEach(Consumer {
      albumList.add(PhotoItem(it.id, it.url, it.thumbnailUrl))
    })
    return albumList
  }
}
