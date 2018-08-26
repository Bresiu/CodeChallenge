package com.bresiu.codechallenge.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.adapters.PostListAdapter
import com.bresiu.codechallenge.di.Injectable
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.fragment.ItemDetailFragment
import com.bresiu.codechallenge.presentation.uimodels.Result
import com.bresiu.codechallenge.presentation.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import java.net.UnknownHostException
import javax.inject.Inject


class ItemListActivity : BaseActivity(), Injectable {
  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var listViewModel: ListViewModel
  private var twoPane: Boolean = false
  private lateinit var adapterPost: PostListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_item_list)
    setupToolbar()
    setupContainer()
    setupRecyclerView(item_list)
    initViewModel()
    observeUIModel()
  }

  private fun observeUIModel() {
    listViewModel.liveData?.observe(this, Observer { uiModel ->
      updateUI(uiModel)
    })
  }

  private fun updateUI(uiModel: Result<List<PostWithUser>>?) {
    when (uiModel?.state) {
      Result.LOADING -> onLoading()
      Result.ERROR -> onError(uiModel.error)
      Result.SUCCESS -> onSuccess(uiModel.bundle!!.unpack())
    }
  }

  private fun onSuccess(resultBundle: List<PostWithUser>) {
    progress_circular.visibility = View.GONE
    adapterPost.submitList(resultBundle)
    if (twoPane) {
      navigateToDetails(resultBundle.first())
    }
  }

  private fun onError(error: Throwable?) {
    progress_circular.visibility = View.GONE
    val errorMessage = if (error is UnknownHostException) {
      getString(R.string.internet_connection_problem)
    } else {
      error?.localizedMessage ?: getString(R.string.unknown_error)
    }
    showToast(errorMessage)
  }

  private fun onLoading() {
    progress_circular.visibility = View.VISIBLE
  }

  private fun setupContainer() {
    if (item_detail_container != null) {
      twoPane = true
    }
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    toolbar.title = title
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    adapterPost = PostListAdapter(this)
    recyclerView.adapter = adapterPost
    recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
  }

  private fun initViewModel() {
    listViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
  }

  fun navigateToDetails(item: PostWithUser) {
    if (twoPane) {
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.item_detail_container, ItemDetailFragment.newInstance(item))
          .commit()
    } else {
      startActivity(ItemDetailActivity.newStartIntent(this, item))
    }
  }

  fun deletePostById(postId: Long) {
    listViewModel.deletePostById(postId)
  }

  private fun showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }
}
