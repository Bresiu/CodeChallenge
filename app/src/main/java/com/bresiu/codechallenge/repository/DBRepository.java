package com.bresiu.codechallenge.repository;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import com.bresiu.codechallenge.data.Dao;
import com.bresiu.codechallenge.data.entity.Album;
import com.bresiu.codechallenge.data.entity.Photo;
import com.bresiu.codechallenge.data.entity.Post;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import com.bresiu.codechallenge.model.UserCombined;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@WorkerThread @Singleton class DBRepository {
	private Dao dao;

	@Inject DBRepository(Dao dao) {
		this.dao = dao;
	}

	void saveUsers(UserCombined userCombined) {
		dao.insertUsers(userCombined.users);
		dao.insertAddresses(userCombined.addresses);
		dao.insertGeos(userCombined.geos);
		dao.insertCompanies(userCombined.companies);
	}

	void savePosts(List<Post> posts) {
		dao.insertPosts(posts);
	}

	void saveAlbums(List<Album> albums) {
		dao.insertAlbums(albums);
	}

	void savePhotos(List<Photo> photos) {
		dao.insertPhotos(photos);
	}

	LiveData<List<PostWithUserAddress>> fetchPostsWithUserAddress() {
		return dao.loadAllPostWithUserAddress();
	}
}