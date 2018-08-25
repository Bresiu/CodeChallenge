package com.bresiu.codechallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bresiu.codechallenge.data.entity.*

@Database(entities = [(User::class), (Address::class), (Geo::class), (Company::class), (Post::class), (Album::class), (Photo::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun dao(): Dao

  companion object {
    const val DATABASE_NAME = "code_challenge_db"
  }
}
