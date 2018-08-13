package com.bresiu.codechallenge.dependencyinjection

import com.bresiu.codechallenge.presentation.viewmodel.ItemListViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    fun itemListViewModel(): ItemListViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }
}