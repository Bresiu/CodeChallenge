package com.bresiu.codechallenge.repository;

import com.bresiu.codechallenge.model.Album;
import com.bresiu.codechallenge.model.Photo;
import com.bresiu.codechallenge.model.Post;
import com.bresiu.codechallenge.model.User;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.http.GET;

@Singleton public class ApiRepository {
	public static final String API_ENDPOINT = "https://jsonplaceholder.typicode.com/";
	private final CodeChallengeService codeChallengeService;

	@Inject ApiRepository(Retrofit retrofit) {
		codeChallengeService = retrofit.create(CodeChallengeService.class);
	}

	public Observable<List<User>> getUsers() {
		return codeChallengeService.getUsers();
	}

	public Observable<List<Album>> getAlbums() {
		return codeChallengeService.getAlbums();
	}

	public Observable<List<Photo>> getPhotos() {
		return codeChallengeService.getPhotos();
	}

	public Observable<List<Post>> getPosts() {
		return codeChallengeService.getPosts();
	}

	public interface CodeChallengeService {
		@GET("users") Observable<List<User>> getUsers();

		@GET("albums") Observable<List<Album>> getAlbums();

		@GET("photos") Observable<List<Photo>> getPhotos();

		@GET("posts") Observable<List<Post>> getPosts();
	}
}
