package com.bresiu.codechallenge.data.entity;

import com.bresiu.codechallenge.model.UserCombined;
import java.util.List;

public class EntitiesCombined {
	public UserCombined userCombined;
	public List<Post> posts;
	public List<Album> albums;
	public List<Photo> photos;

	public EntitiesCombined(UserCombined userCombined, List<Post> posts, List<Album> albums,
			List<Photo> photos) {
		this.userCombined = userCombined;
		this.posts = posts;
		this.albums = albums;
		this.photos = photos;
	}
}
