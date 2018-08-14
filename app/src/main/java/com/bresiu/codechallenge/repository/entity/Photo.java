package com.bresiu.codechallenge.repository.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

public class Photo {
	@PrimaryKey long id;
	@ColumnInfo(name = "album_id") long albumId;
	String title;
	String url;
	@ColumnInfo(name = "thumbnail_url") String thumbnailUrl;

	public Photo(long id, long albumId, String title, String url, String thumbnailUrl) {
		this.id = id;
		this.albumId = albumId;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
	}
}
