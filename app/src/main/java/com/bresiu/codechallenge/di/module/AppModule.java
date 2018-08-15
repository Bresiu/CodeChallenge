package com.bresiu.codechallenge.di.module;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import com.bresiu.codechallenge.data.AppDatabase;
import com.bresiu.codechallenge.data.Dao;
import com.bresiu.codechallenge.di.component.ViewModelSubComponent;
import com.bresiu.codechallenge.presentation.viewmodel.ViewModelFactory;
import com.bresiu.codechallenge.repository.ApiRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class) public class AppModule {

	@Singleton @Provides AppDatabase database(Application application) {
		return Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
	}

	@Singleton @Provides ViewModelProvider.Factory viewModelFactory(
			ViewModelSubComponent.Builder viewModelSubComponent) {
		return new ViewModelFactory(viewModelSubComponent.build());
	}

	@Singleton @Provides Retrofit retrofit() {
		return new Retrofit.Builder().baseUrl(ApiRepository.API_ENDPOINT)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	@Singleton @Provides Dao dao(AppDatabase database) {
		return database.dao();
	}
}
