package com.bresiu.codechallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bresiu.codechallenge.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailViewModel @Inject internal constructor(private val repository: Repository) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val text: Any?
        get() = "test"

}
