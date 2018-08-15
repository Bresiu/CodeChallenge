package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index("albumId")}, tableName = "photos", foreignKeys = @ForeignKey(entity = Album.class, parentColumns = "id", childColumns = "albumId", onDelete = ForeignKey.CASCADE))
public class Photo {
	@PrimaryKey public long id;
	public long albumId;
	public String title;
	public String url;
	public String thumbnailUrl;

	public Photo(long id, long albumId, String title, String url, String thumbnailUrl) {
		this.id = id;
		this.albumId = albumId;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
	}
}
