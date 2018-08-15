package com.bresiu.codechallenge.repository.mappers;

import com.bresiu.codechallenge.data.entity.Post;

public class PostModelToEntityMapper
		implements Mapper.ObjectMapper<Post, com.bresiu.codechallenge.model.Post> {
	@Override public Post map(com.bresiu.codechallenge.model.Post modelPost) {
		return new Post(modelPost.id, modelPost.userId, modelPost.title, modelPost.body);
	}
}