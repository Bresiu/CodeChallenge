package com.bresiu.codechallenge.repository

import com.bresiu.codechallenge.model.Album
import com.bresiu.codechallenge.model.Photo
import com.bresiu.codechallenge.model.Post
import com.bresiu.codechallenge.model.User
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject internal constructor(retrofit: Retrofit) {
    private val codeChallengeService: CodeChallengeService

    internal val users: Observable<List<User>>
        get() = codeChallengeService.users

    internal val albums: Observable<List<Album>>
        get() = codeChallengeService.albums

    internal val photos: Observable<List<Photo>>
        get() = codeChallengeService.photos

    internal val posts: Observable<List<Post>>
        get() = codeChallengeService.posts

    init {
        codeChallengeService = retrofit.create(CodeChallengeService::class.java)
    }

    interface CodeChallengeService {
        @get:GET("users")
        val users: Observable<List<User>>

        @get:GET("albums")
        val albums: Observable<List<Album>>

        @get:GET("photos")
        val photos: Observable<List<Photo>>

        @get:GET("posts")
        val posts: Observable<List<Post>>
    }

    companion object {
        const val API_ENDPOINT = "https://jsonplaceholder.typicode.com/"
    }
}
