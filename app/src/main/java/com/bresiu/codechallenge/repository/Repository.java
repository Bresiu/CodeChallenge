package com.bresiu.codechallenge.repository;

import android.util.Log;
import com.bresiu.codechallenge.model.Album;
import com.bresiu.codechallenge.model.Photo;
import com.bresiu.codechallenge.model.Post;
import com.bresiu.codechallenge.model.Response;
import com.bresiu.codechallenge.model.User;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
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

	public Disposable fetchAndSaveData() {
		return Observable.zip(apiRepository.getPosts(), apiRepository.getUsers(),
				apiRepository.getAlbums(), apiRepository.getPhotos(),
				new Function4<List<Post>, List<User>, List<Album>, List<Photo>, Response>() {
					@Override public Response apply(List<Post> posts, List<User> users, List<Album> albums,
							List<Photo> photos) {
						return new Response(posts, users, albums, photos);
					}
				})
				.observeOn(Schedulers.io())
				.map(new Function<Response, Object>() {
					@Override public Object apply(Response response) throws Exception {
						return null;
					}
				})
				.subscribeOn(Schedulers.io())
				.subscribe(new Consumer<Response>() {
					@Override public void accept(Response response) {
						response.users.forEach(new java.util.function.Consumer<User>() {
							@Override public void accept(User user) {
								Log.d("BRS", user.toString());
							}
						});
						dbRepository.saveUsers(response.users);
						dbRepository.savePosts(response.posts);
						dbRepository.saveAlbums(response.albums);
						dbRepository.savePhotos(response.photos);
					}
				}, new Consumer<Throwable>() {
					@Override public void accept(Throwable throwable) {
						Log.e("BRS", "Problem with fetching and saving data", throwable);
					}
				});
	}
}
