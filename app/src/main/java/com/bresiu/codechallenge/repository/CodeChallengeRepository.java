package com.bresiu.codechallenge.repository;

import com.bresiu.codechallenge.db.AppDatabase;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CodeChallengeRepository {
	private final AppDatabase appDatabase;

	@Inject CodeChallengeRepository(AppDatabase appDatabase) {
		this.appDatabase = appDatabase;
	}


}
