package com.bresiu.codechallenge.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.bresiu.codechallenge.data.entity.EntitiesCombined
import com.bresiu.codechallenge.model.*
import com.bresiu.codechallenge.repository.mappers.*
import io.reactivex.Observable
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject internal constructor(private val dbRepository: DBRepository,
                                              private val apiRepository: ApiRepository) {

  fun fetchData(): Observable<EntitiesCombined> {
    return Observable.zip(apiRepository.posts, apiRepository.users,
        apiRepository.albums, apiRepository.photos,
        Function4<List<Post>, List<User>, List<Album>, List<Photo>,
            Response> { posts, users, albums, photos ->
          Response(posts, users, albums, photos)
        }).observeOn(Schedulers.io())
        .map { response ->
          EntitiesCombined(
              Mapper.mapObject(response.users, UserCombinedModelToEntitiesMapper()),
              Mapper.mapList(response.posts, PostModelToEntityMapper()),
              Mapper.mapList(response.albums, AlbumModelToEntityMapper()),
              Mapper.mapList(response.photos, PhotoModelToEntityMapper()))
        }
        .subscribeOn(Schedulers.io())
  }

  fun deletePostById(postId: Long) {
    dbRepository.deletePostById(postId)
  }

  fun saveData(entitiesCombined: EntitiesCombined) {
    dbRepository.saveUsers(entitiesCombined.userCombined)
    dbRepository.savePosts(entitiesCombined.posts)
    dbRepository.saveAlbums(entitiesCombined.albums)
    dbRepository.savePhotos(entitiesCombined.photos)
  }

  fun searchDataForPhrase(phrase: String): LiveData<List<PostWithUser>> {
    return dbRepository.searchData(phrase)
  }

  fun getAllData(): LiveData<List<PostWithUser>> {
    return dbRepository.fetchPostsWithUser()
  }

  fun makeAlbumLiveData(userId: Long): LiveData<List<AlbumListItem>> {
    return LiveDataReactiveStreams.fromPublisher(
        dbRepository.fetchAlbumsForUser(userId)
            .filter {
              it.isNotEmpty()
            }.map {
              Mapper.unfoldList(it, AlbumWithPhotosToAlbumListItemMapper())
            }
    )
  }
}