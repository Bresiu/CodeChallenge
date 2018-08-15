package com.bresiu.codechallenge.di.module;

import com.bresiu.codechallenge.presentation.activity.ItemDetailActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module public abstract class ItemDetailActivityModule {
	@ContributesAndroidInjector abstract ItemDetailActivity contributeItemDetailActivity();
}
