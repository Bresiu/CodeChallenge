package com.bresiu.codechallenge.di.component

import com.bresiu.codechallenge.presentation.viewmodel.ItemDetailViewModel
import com.bresiu.codechallenge.presentation.viewmodel.ItemListViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    fun itemListViewModel(): ItemListViewModel
    fun itemDetailViewModel(): ItemDetailViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}