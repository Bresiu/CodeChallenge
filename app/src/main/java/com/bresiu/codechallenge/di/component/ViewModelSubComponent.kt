package com.bresiu.codechallenge.di.component

import com.bresiu.codechallenge.presentation.viewmodel.DetailViewModel
import com.bresiu.codechallenge.presentation.viewmodel.ListViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    fun itemListViewModel(): ListViewModel
    fun detailViewModel(): DetailViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}