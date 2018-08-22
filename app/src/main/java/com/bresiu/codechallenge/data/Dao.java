package com.bresiu.codechallenge.data;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.bresiu.codechallenge.data.entity.Address;
import com.bresiu.codechallenge.data.entity.Album;
import com.bresiu.codechallenge.data.entity.Company;
import com.bresiu.codechallenge.data.entity.Geo;
import com.bresiu.codechallenge.data.entity.Photo;
import com.bresiu.codechallenge.data.entity.Post;
import com.bresiu.codechallenge.data.entity.User;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import java.util.List;

@WorkerThread @androidx.room.Dao public interface Dao {
	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertUsers(List<User> users);

	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertAddresses(List<Address> addresses);

	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertGeos(List<Geo> geos);

	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertCompanies(List<Company> companies);

	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertAlbums(List<Album> albums);

	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertPhotos(List<Photo> photos);

	@Insert(onConflict = OnConflictStrategy.REPLACE) void insertPosts(List<Post> posts);

	@Query("DELETE FROM posts WHERE id = :postId") void deletePostById(long postId);

	@Query("SELECT posts.id AS postId, posts.title AS postTitle, posts.body AS postBody, users.email AS userEmail FROM posts, users WHERE posts.userId = users.id")
	LiveData<List<PostWithUserAddress>> loadAllPostWithUserAddress();
}
