package com.bresiu.codechallenge.model;

import java.util.List;

public class Response {
	public List<Post> posts;
	public List<User> users;
	public List<Album> albums;
	public List<Photo> photos;

	public Response(List<Post> posts, List<User> users, List<Album> albums, List<Photo> photos) {
		this.posts = posts;
		this.users = users;
		this.albums = albums;
		this.photos = photos;
	}
}
