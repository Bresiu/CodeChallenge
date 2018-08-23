package com.bresiu.codechallenge.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
		@Index("albumId")
}, tableName = "photos", foreignKeys = @ForeignKey(entity = Album.class, parentColumns = "id", childColumns = "albumId", onDelete = ForeignKey.CASCADE))
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