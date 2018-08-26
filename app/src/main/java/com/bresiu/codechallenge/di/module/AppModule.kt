package com.bresiu.codechallenge.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bresiu.codechallenge.data.AppDatabase
import com.bresiu.codechallenge.data.Dao
import com.bresiu.codechallenge.di.component.ViewModelSubComponent
import com.bresiu.codechallenge.presentation.viewmodel.ViewModelFactory
import com.bresiu.codechallenge.repository.ApiRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@SuppressWarnings("unused")
@Module(subcomponents = [(ViewModelSubComponent::class)])
class AppModule {

  @Singleton
  @Provides
  internal fun database(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
  }

  @Singleton
  @Provides
  internal fun viewModelFactory(
      viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
    return ViewModelFactory(viewModelSubComponent.build())
  }

  @Singleton
  @Provides
  internal fun retrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(ApiRepository.API_ENDPOINT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }

  @Singleton
  @Provides
  internal fun dao(database: AppDatabase): Dao {
    return database.dao()
  }
}
