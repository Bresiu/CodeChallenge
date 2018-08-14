package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Album.class, parentColumns = "id", childColumns = "album_id", onDelete = ForeignKey.CASCADE))
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

	public long getId() {
		return id;
	}

	public long getAlbumId() {
		return albumId;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
}
