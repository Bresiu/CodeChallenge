package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(
		indices = {@Index("userId")},
		tableName = "posts",
		foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
)
public class Post {
	@PrimaryKey public long id;
	public long userId;
	public String title;
	public String body;

	public Post(long id, long userId, String title, String body) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
	}
}
