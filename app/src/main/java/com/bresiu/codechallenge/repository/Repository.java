package com.bresiu.codechallenge.repository;

import androidx.lifecycle.LiveData;
import com.bresiu.codechallenge.data.entity.EntitiesCombined;
import com.bresiu.codechallenge.model.Album;
import com.bresiu.codechallenge.model.Photo;
import com.bresiu.codechallenge.model.Post;
import com.bresiu.codechallenge.model.PostWithUserAddress;
import com.bresiu.codechallenge.model.Response;
import com.bresiu.codechallenge.model.User;
import com.bresiu.codechallenge.repository.mappers.AlbumModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.Mapper;
import com.bresiu.codechallenge.repository.mappers.PhotoModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.PostModelToEntityMapper;
import com.bresiu.codechallenge.repository.mappers.UserCombinedModelToEntitiesMapper;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
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
				apiRepository.getAlbums(), apiRepository.getPhotos(),
				new Function4<List<Post>, List<User>, List<Album>, List<Photo>, Response>() {
					@Override public Response apply(List<Post> posts, List<User> users, List<Album> albums,
							List<Photo> photos) {
						return new Response(posts, users, albums, photos);
					}
				}).observeOn(Schedulers.io()).map(new Function<Response, EntitiesCombined>() {
			@Override public EntitiesCombined apply(Response response) {
				return new EntitiesCombined(
						Mapper.mapObject(response.users, new UserCombinedModelToEntitiesMapper()),
						Mapper.mapList(response.posts, new PostModelToEntityMapper()),
						Mapper.mapList(response.albums, new AlbumModelToEntityMapper()),
						Mapper.mapList(response.photos, new PhotoModelToEntityMapper()));
			}
		}).subscribeOn(Schedulers.io());
	}

	public void saveData(EntitiesCombined entitiesCombined) {
		dbRepository.saveUsers(entitiesCombined.userCombined);
		dbRepository.savePosts(entitiesCombined.posts);
		dbRepository.saveAlbums(entitiesCombined.albums);
		dbRepository.savePhotos(entitiesCombined.photos);
	}

	public LiveData<List<PostWithUserAddress>> getLiveData() {
		return dbRepository.fetchPostsWithUserAddress();
	}
}
