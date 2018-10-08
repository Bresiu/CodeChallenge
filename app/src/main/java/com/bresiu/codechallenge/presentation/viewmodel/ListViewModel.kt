package com.bresiu.codechallenge.presentation.viewmodel

import androidx.lifecycle.*
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.uimodels.NoResultsFoundException
import com.bresiu.codechallenge.presentation.uimodels.Result
import com.bresiu.codechallenge.presentation.uimodels.ResultBundle
import com.bresiu.codechallenge.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ListViewModel @Inject internal constructor(private val repository: Repository) : ViewModel() {
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()
  private var subject: BehaviorSubject<Long> = BehaviorSubject.create()
  private var mediatorLiveData: MediatorLiveData<Result<List<PostWithUser>>> = MediatorLiveData()
  private val searchInput: MutableLiveData<String> = MutableLiveData()
  private val sourceSwitcher = Transformations.switchMap(searchInput) {
    if (it.isNotEmpty()) {
      repository.searchDataForPhrase(formatPhrase(it))
    } else {
      repository.getAllData()
    }
  }

  private fun formatPhrase(phrase: String): String {
    return "%$phrase%"
  }

  val liveData: LiveData<Result<List<PostWithUser>>>?
    get() = mediatorLiveData

  init {
    initLiveData()
    initListeners()
    fetchData()
  }

  private fun initListeners() {
    compositeDisposable.add(subject.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe { repository.deletePostById(it) })
  }

  private fun initLiveData() {
    mediatorLiveData.addSource(sourceSwitcher) { postWithUserAddresses ->
      postDataIfNotEmpty(postWithUserAddresses)
    }
    onSearchViewCollapsed()
    mediatorLiveData.value = Result.loadingResult()
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }

  private fun postDataIfNotEmpty(data: List<PostWithUser>) {
    if (data.isNotEmpty()) {
      mediatorLiveData.postValue(Result.successResult(ResultBundle(data)))
    } else if (!searchInput.value.isNullOrBlank()) {
      mediatorLiveData.postValue(Result.errorResult(NoResultsFoundException()))
    }
  }

  private fun fetchData() {
    compositeDisposable.add(repository.fetchData()
        .subscribe({
          repository.saveData(it)
        }, {
          mediatorLiveData.postValue(Result.errorResult(it))
        }))
  }

  fun deletePostById(postId: Long) {
    subject.onNext(postId)
  }

  fun onSearchViewCollapsed() {
    searchInput.postValue("")
  }

  fun searchForData(query: String) {
    searchInput.postValue(query)
  }
}
