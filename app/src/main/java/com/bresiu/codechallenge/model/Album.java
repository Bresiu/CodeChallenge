package com.bresiu.codechallenge.model;

public class Album {
	public long id;
	public long userId;
	public String title;

	public Album(long id, long userId, String title) {
		this.id = id;
		this.userId = userId;
		this.title = title;
	}
}
