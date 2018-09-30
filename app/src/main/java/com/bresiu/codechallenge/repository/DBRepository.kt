package com.bresiu.codechallenge.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.bresiu.codechallenge.data.Dao
import com.bresiu.codechallenge.data.entity.Album
import com.bresiu.codechallenge.data.entity.AlbumWithPhotos
import com.bresiu.codechallenge.data.entity.Photo
import com.bresiu.codechallenge.data.entity.Post
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.model.UserCombined
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@WorkerThread
@Singleton
internal class DBRepository @Inject constructor(private val dao: Dao) {

  fun saveUsers(userCombined: UserCombined) {
    dao.insertUsers(userCombined.users)
    dao.insertAddresses(userCombined.addresses)
    dao.insertGeos(userCombined.geos)
    dao.insertCompanies(userCombined.companies)
  }

  fun savePosts(posts: List<Post>) {
    dao.insertPosts(posts)
  }

  fun saveAlbums(albums: List<Album>) {
    dao.insertAlbums(albums)
  }

  fun savePhotos(photos: List<Photo>) {
    dao.insertPhotos(photos)
  }

  fun deletePostById(postId: Long) {
    dao.deletePostById(postId)
  }

  fun fetchPostsWithUser(): LiveData<List<PostWithUser>> {
    return dao.loadAllPostWithUser()
  }

  fun fetchAlbumsForUser(userId: Long): Flowable<List<AlbumWithPhotos>> {
    return dao.getAlbumsForUser(userId)
  }

  fun searchData(phrase: String): LiveData<List<PostWithUser>> {
    return dao.searchData(phrase)
  }
}