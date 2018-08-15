package com.bresiu.codechallenge.model;

public class Photo {
	public int id;
	public int albumId;
	public String title;
	public String url;
	public String thumbnailUrl;

	public Photo(int id, int albumId, String title, String url, String thumbnailUrl) {
		this.id = id;
		this.albumId = albumId;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
	}
}
