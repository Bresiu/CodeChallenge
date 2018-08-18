package com.bresiu.codechallenge.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("userId")}, tableName = "albums", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE))
public class Album {
	@PrimaryKey public int id;
	public int userId;
	public String title;

	public Album(int id, int userId, String title) {
		this.id = id;
		this.userId = userId;
		this.title = title;
	}
}
