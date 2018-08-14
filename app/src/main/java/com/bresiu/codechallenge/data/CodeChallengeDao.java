package com.bresiu.codechallenge.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.support.annotation.WorkerThread;
import com.bresiu.codechallenge.data.entity.Album;
import com.bresiu.codechallenge.data.entity.Photo;
import com.bresiu.codechallenge.data.entity.Post;
import com.bresiu.codechallenge.data.entity.User;
import java.util.List;

@Dao public interface CodeChallengeDao {
	@WorkerThread @Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insertUsers(List<User> users);

	@WorkerThread @Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insertAlbums(List<Album> albums);

	@WorkerThread @Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insertPhotos(List<Photo> photos);

	@WorkerThread @Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insertPosts(List<Post> posts);
}
