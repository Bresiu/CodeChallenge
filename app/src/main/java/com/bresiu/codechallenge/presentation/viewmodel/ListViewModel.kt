package com.bresiu.codechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.uimodels.Result
import com.bresiu.codechallenge.presentation.uimodels.ResultBundle
import com.bresiu.codechallenge.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ListViewModel @Inject internal constructor(private val repository: Repository) : ViewModel() {
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()
  private var subject: BehaviorSubject<Long>? = null
  private var mediatorLiveData: MediatorLiveData<Result<List<PostWithUser>>>? = null

  val liveData: LiveData<Result<List<PostWithUser>>>?
    get() = mediatorLiveData

  init {
    initLiveData()
    initListeners()
    fetchData()
  }

  private fun initListeners() {
    subject = BehaviorSubject.create()
    compositeDisposable.add(subject!!.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe { repository.deletePostById(it) })
  }

  private fun initLiveData() {
    mediatorLiveData = MediatorLiveData()
    mediatorLiveData!!.addSource(repository.postsUpdates) { postWithUserAddresses ->
      if (postWithUserAddresses.isNotEmpty()) {
        mediatorLiveData!!.postValue(Result.successResult(ResultBundle(postWithUserAddresses)))
      }
    }
    mediatorLiveData!!.value = Result.loadingResult()
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }

  private fun fetchData() {
    compositeDisposable.add(repository.fetchData()
        .subscribe({
          repository.saveData(it)
        }, {
          mediatorLiveData!!.postValue(Result.errorResult(it))
        }))
  }

  fun deletePostById(postId: Long) {
    subject!!.onNext(postId)
  }
}
