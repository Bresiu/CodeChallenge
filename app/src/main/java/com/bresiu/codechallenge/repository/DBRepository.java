package com.bresiu.codechallenge.repository;

import android.support.annotation.WorkerThread;
import com.bresiu.codechallenge.data.Dao;
import com.bresiu.codechallenge.model.Album;
import com.bresiu.codechallenge.model.Photo;
import com.bresiu.codechallenge.model.Post;
import com.bresiu.codechallenge.model.User;
import com.bresiu.codechallenge.model.UserCombined;
import com.bresiu.codechallenge.repository.mappers.AlbumModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.Mapper;
import com.bresiu.codechallenge.repository.mappers.PhotoModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.PostModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.UserCombinedModelToEntitiesMapper;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@WorkerThread @Singleton class DBRepository {
	private Dao dao;

	@Inject DBRepository(Dao dao) {
		this.dao = dao;
	}

	void saveUsers(List<User> users) {
		UserCombined userCombined = Mapper.mapObject(users, new UserCombinedModelToEntitiesMapper());
		dao.insertUsers(userCombined.users);
		dao.insertAddresses(userCombined.addresses);
		dao.insertGeos(userCombined.geos);
		dao.insertCompanies(userCombined.companies);
	}

	void savePosts(List<Post> posts) {
		dao.insertPosts(Mapper.mapList(posts, new PostModelToEntityMapper()));
	}

	void saveAlbums(List<Album> albums) {
		dao.insertAlbums(Mapper.mapList(albums, new AlbumModelToEntityMapper()));
	}

	void savePhotos(List<Photo> photos) {
		dao.insertPhotos(Mapper.mapList(photos, new PhotoModelToEntityMapper()));
	}
}
