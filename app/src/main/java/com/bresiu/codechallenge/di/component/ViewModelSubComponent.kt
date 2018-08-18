package com.bresiu.codechallenge.di.component

import com.bresiu.codechallenge.presentation.viewmodel.ListViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    fun itemListViewModel(): ListViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}