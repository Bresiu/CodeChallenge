package com.bresiu.codechallenge.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.adapters.AlbumListAdapter
import com.bresiu.codechallenge.data.entity.AlbumWithPhotos
import com.bresiu.codechallenge.databinding.PostDetailBinding
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.viewmodel.DetailViewModel
import com.bresiu.codechallenge.presentation.views.HeaderItemDecoration
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.post_detail.*
import javax.inject.Inject


class ItemDetailFragment : BaseFragment() {
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var detailViewModel: DetailViewModel
  private lateinit var albumListAdapter: AlbumListAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return DataBindingUtil
        .inflate<PostDetailBinding>(inflater, R.layout.post_detail, container, false)
        .apply { post = getItem(arguments) }
        .root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initViewModel()
    setupRecyclerView(post_detail_album)
  }

  private fun initViewModel() {
    detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
    detailViewModel.getDetailUpdatesForUser(getItem(arguments).userId).observe(viewLifecycleOwner,
        Observer { albumListItems ->
          updateUI(albumListItems)
        })
  }

  private fun updateUI(albumWithPhotos: PagedList<AlbumWithPhotos>) {
    Log.d("BRS", "update ui size: " + albumWithPhotos.size)
    albumListAdapter.submitList(albumWithPhotos)
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    albumListAdapter = AlbumListAdapter(Glide.with(this))
    with(recyclerView) {
      adapter = albumListAdapter
      addItemDecoration(HeaderItemDecoration(recyclerView, albumListAdapter))
    }
  }

  companion object {
    private const val ARG_ITEM = "item"
    fun newInstance(item: PostWithUser): ItemDetailFragment {
      val bundle = Bundle().apply { putParcelable(ARG_ITEM, item) }
      return ItemDetailFragment().apply { arguments = bundle }
    }

    fun getItem(arguments: Bundle?): PostWithUser {
      return arguments?.getParcelable(ARG_ITEM)
          ?: throw IllegalStateException("PostWithUser argument is missing")
    }
  }
}
