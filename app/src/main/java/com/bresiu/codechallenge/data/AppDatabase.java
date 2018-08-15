package com.bresiu.codechallenge.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.bresiu.codechallenge.data.entity.Address;
import com.bresiu.codechallenge.data.entity.Album;
import com.bresiu.codechallenge.data.entity.Company;
import com.bresiu.codechallenge.data.entity.Geo;
import com.bresiu.codechallenge.data.entity.Photo;
import com.bresiu.codechallenge.data.entity.Post;
import com.bresiu.codechallenge.data.entity.User;

@Database(entities = {
		User.class, Address.class, Geo.class, Company.class, Post.class, Album.class, Photo.class
}, version = 1, exportSchema = false) public abstract class AppDatabase extends RoomDatabase {
	public static final String DATABASE_NAME = "code_challenge_db";

	public abstract Dao dao();
}
