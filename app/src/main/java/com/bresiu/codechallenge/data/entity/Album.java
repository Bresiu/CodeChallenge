package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class Album {
	@PrimaryKey long id;
	@ColumnInfo(name = "user_id") long userId;
	String title;

	public Album(long id, long userId, String title) {
		this.id = id;
		this.userId = userId;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}
}
