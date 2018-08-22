package com.bresiu.codechallenge.di.module

import com.bresiu.codechallenge.presentation.activity.ItemListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ItemListModule {
    @ContributesAndroidInjector
    internal abstract fun contributeItemListActivity(): ItemListActivity
}