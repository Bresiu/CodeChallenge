package com.bresiu.codechallenge.di.module

import com.bresiu.codechallenge.presentation.activity.ItemDetailActivity
import com.bresiu.codechallenge.presentation.fragment.ItemDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ItemDetailModule {
    @ContributesAndroidInjector
    internal abstract fun contributeItemDetailFragment(): ItemDetailFragment
    @ContributesAndroidInjector
    internal abstract fun contributeItemDetailActivity(): ItemDetailActivity
}