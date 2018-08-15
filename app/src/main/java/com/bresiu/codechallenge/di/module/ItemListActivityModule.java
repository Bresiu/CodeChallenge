package com.bresiu.codechallenge.di.module;

import com.bresiu.codechallenge.presentation.activity.ItemListActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module public abstract class ItemListActivityModule {
	@ContributesAndroidInjector abstract ItemListActivity contributeItemListActivity();
}
