package com.bresiu.codechallenge.repository.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class Post {
	@PrimaryKey long id;
	@ColumnInfo(name = "user_id") long userId;
	String title;
	String body;
}
