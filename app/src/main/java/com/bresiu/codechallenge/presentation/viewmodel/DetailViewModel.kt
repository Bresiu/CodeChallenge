package com.bresiu.codechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bresiu.codechallenge.model.AlbumListItem
import com.bresiu.codechallenge.repository.Repository
import javax.inject.Inject

class DetailViewModel @Inject internal constructor(
    private val repository: Repository) : ViewModel() {

  fun getDetailUpdatesForUser(userId: Long): LiveData<List<AlbumListItem>> {
    return repository.makeAlbumLiveData(userId)
  }
}
