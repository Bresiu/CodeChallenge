package com.bresiu.codechallenge.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.bresiu.codechallenge.db.entity.Album;
import com.bresiu.codechallenge.db.entity.Post;
import com.bresiu.codechallenge.db.entity.User;

@Database(entities = { User.class, Post.class, Album.class, }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public static final String DATABASE_NAME = "code_challenge_db";
}
