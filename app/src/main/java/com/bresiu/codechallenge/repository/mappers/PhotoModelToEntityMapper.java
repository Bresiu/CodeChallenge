package com.bresiu.codechallenge.repository.mappers;

import com.bresiu.codechallenge.data.entity.Photo;

public class PhotoModelToEntityMapper
		implements Mapper.ObjectMapper<Photo, com.bresiu.codechallenge.model.Photo> {

	@Override public Photo map(com.bresiu.codechallenge.model.Photo modelPhoto) {
		return new Photo(modelPhoto.id, modelPhoto.albumId, modelPhoto.title, modelPhoto.url,
				modelPhoto.thumbnailUrl);
	}
}
