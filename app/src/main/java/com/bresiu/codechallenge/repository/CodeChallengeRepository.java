package com.bresiu.codechallenge.repository;

import com.bresiu.codechallenge.data.AppDatabase;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CodeChallengeRepository {
	private final AppDatabase appDatabase;
	private final ApiRepository apiRepository;

	@Inject CodeChallengeRepository(AppDatabase appDatabase, ApiRepository apiRepository) {
		this.appDatabase = appDatabase;
		this.apiRepository = apiRepository;
	}

	public void fetchAndSaveData() {
	}
}
