package com.bresiu.codechallenge.repository.mappers

import com.bresiu.codechallenge.data.entity.Photo

class PhotoModelToEntityMapper : Mapper.ObjectMapper<Photo, com.bresiu.codechallenge.model.Photo> {

    override fun map(`object`: com.bresiu.codechallenge.model.Photo): Photo {
        return Photo(`object`.id.toLong(), `object`.albumId.toLong(), `object`.title, `object`.url,
                `object`.thumbnailUrl)
    }
}