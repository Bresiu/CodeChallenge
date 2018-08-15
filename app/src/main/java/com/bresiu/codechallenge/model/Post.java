package com.bresiu.codechallenge.model;

public class Post {
	public int id;
	public int userId;
	public String title;
	public String body;

	public Post(int id, int userId, String title, String body) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
	}

	@Override public String toString() {
		return "Post{"
				+ "id="
				+ id
				+ ", userId="
				+ userId
				+ ", title='"
				+ title
				+ '\''
				+ ", body='"
				+ body
				+ '\''
				+ '}';
	}
}
