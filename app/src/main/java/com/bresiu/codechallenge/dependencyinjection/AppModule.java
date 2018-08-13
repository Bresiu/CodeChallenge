package com.bresiu.codechallenge.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import com.bresiu.codechallenge.db.AppDatabase;
import com.bresiu.codechallenge.presentation.viewmodel.CodeChallengeViewModelFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(subcomponents = ViewModelSubComponent.class) class AppModule {

	@Singleton @Provides AppDatabase database(Application application) {
		return Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
	}

	@Singleton @Provides ViewModelProvider.Factory viewModelFactory(
			ViewModelSubComponent.Builder viewModelSubComponent) {
		return new CodeChallengeViewModelFactory(viewModelSubComponent.build());
	}
}
