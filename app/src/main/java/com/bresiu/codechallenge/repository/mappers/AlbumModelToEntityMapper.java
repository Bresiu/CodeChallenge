package com.bresiu.codechallenge.repository.mappers;

import com.bresiu.codechallenge.data.entity.Album;

public class AlbumModelToEntityMapper
		implements Mapper.ObjectMapper<Album, com.bresiu.codechallenge.model.Album> {

	@Override public Album map(com.bresiu.codechallenge.model.Album modelAlbum) {
		return new Album(modelAlbum.id, modelAlbum.userId, modelAlbum.title);
	}
}
