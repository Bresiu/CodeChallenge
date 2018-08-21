package com.bresiu.codechallenge.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import com.bresiu.codechallenge.data.entity.EntitiesCombined;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import com.bresiu.codechallenge.model.Response;
import com.bresiu.codechallenge.repository.mappers.AlbumModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.Mapper;
import com.bresiu.codechallenge.repository.mappers.PhotoModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.PostModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.UserCombinedModelToEntitiesMapper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class Repository {
	private final DBRepository dbRepository;
	private final ApiRepository apiRepository;

	@Inject Repository(DBRepository dbRepository, ApiRepository apiRepository) {
		this.dbRepository = dbRepository;
		this.apiRepository = apiRepository;
	}

	public Observable<EntitiesCombined> fetchData() {
		return Observable.zip(apiRepository.getPosts(), apiRepository.getUsers(),
				apiRepository.getAlbums(), apiRepository.getPhotos(), Response::new)
				.observeOn(Schedulers.io())
				.map(response -> new EntitiesCombined(
						Mapper.INSTANCE.mapObject(response.users, new UserCombinedModelToEntitiesMapper()),
						Mapper.INSTANCE.mapList(response.posts, new PostModelToEntityMapper()),
						Mapper.INSTANCE.mapList(response.albums, new AlbumModelToEntityMapper()),
						Mapper.INSTANCE.mapList(response.photos, new PhotoModelToEntityMapper())))
				.subscribeOn(Schedulers.io());
	}

	public void saveData(EntitiesCombined entitiesCombined) {
		Log.d("BRS", "saveData");
		dbRepository.saveUsers(entitiesCombined.userCombined);
		dbRepository.savePosts(entitiesCombined.posts);
		dbRepository.saveAlbums(entitiesCombined.albums);
		dbRepository.savePhotos(entitiesCombined.photos);
	}

	public LiveData<List<PostWithUserAddress>> getLiveData() {
		return dbRepository.fetchPostsWithUserAddress();
	}

	public void deletePostById(long postId) {
		dbRepository.deletePostById(postId);
	}
}
