package com.bresiu.codechallenge.dependencyinjection;

import com.bresiu.codechallenge.presentation.activity.ItemListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module abstract class ItemListActivityModule {
	@ContributesAndroidInjector abstract ItemListActivity contributeItemListActivity();
}
