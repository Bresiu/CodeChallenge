package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

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
