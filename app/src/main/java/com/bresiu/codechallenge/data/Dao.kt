package com.bresiu.codechallenge.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bresiu.codechallenge.data.entity.*
import com.bresiu.codechallenge.data.entity.AlbumWithPhotos
import com.bresiu.codechallenge.model.PostWithUser

@WorkerThread
@androidx.room.Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddresses(addresses: List<Address>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGeos(geos: List<Geo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompanies(companies: List<Company>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Query("DELETE FROM posts WHERE id = :postId")
    fun deletePostById(postId: Long)

    @Query("SELECT posts.id AS postId, posts.title AS postTitle, posts.body AS postBody, users.email AS userEmail, users.id AS userId FROM posts, users WHERE posts.userId = users.id")
    fun loadAllPostWithUser(): LiveData<List<PostWithUser>>

    @Transaction
    @Query("SELECT albums.id AS id, albums.title AS albumTitle FROM albums WHERE albums.userId = :userId")
    fun getAlbumsForUser(userId: Long): LiveData<List<AlbumWithPhotos>>
}
