package com.bresiu.codechallenge.repository;

import android.arch.lifecycle.LiveData;
import android.os.Looper;
import android.util.Log;
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
import io.reactivex.functions.Consumer;
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
		Log.d("BRS", "fetchData Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
		return Observable.zip(apiRepository.getPosts(), apiRepository.getUsers(),
				apiRepository.getAlbums(), apiRepository.getPhotos(),
				new Function4<List<Post>, List<User>, List<Album>, List<Photo>, Response>() {
					@Override public Response apply(List<Post> posts, List<User> users, List<Album> albums,
							List<Photo> photos) {
						Log.d("BRS", "zip Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
						return new Response(posts, users, albums, photos);
					}
				}).observeOn(Schedulers.io())
				// TODO: Remove finally
				.doOnNext(new Consumer<Response>() {
					@Override public void accept(Response response) {
						Log.d("BRS", "doOnNext Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
						response.users.forEach(new java.util.function.Consumer<User>() {
							@Override public void accept(User user) {
								Log.d("BRS", user.toString());
							}
						});
					}
				}).map(new Function<Response, EntitiesCombined>() {
					@Override public EntitiesCombined apply(Response response) {
						Log.d("BRS", "map Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
						return new EntitiesCombined(
								Mapper.mapObject(response.users, new UserCombinedModelToEntitiesMapper()),
								Mapper.mapList(response.posts, new PostModelToEntityMapper()),
								Mapper.mapList(response.albums, new AlbumModelToEntityMapper()),
								Mapper.mapList(response.photos, new PhotoModelToEntityMapper()));
					}
				}).subscribeOn(Schedulers.io());
	}

	public void saveData(EntitiesCombined entitiesCombined) {
		Log.d("BRS", "saveData Main thread: " + (Looper.myLooper() == Looper.getMainLooper()));
		dbRepository.saveUsers(entitiesCombined.userCombined);
		dbRepository.savePosts(entitiesCombined.posts);
		dbRepository.saveAlbums(entitiesCombined.albums);
		dbRepository.savePhotos(entitiesCombined.photos);
	}

	public LiveData<List<PostWithUserAddress>> getLiveData() {
		return dbRepository.fetchPostsWithUserAddress();
	}
}
