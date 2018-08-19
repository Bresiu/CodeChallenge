package com.bresiu.codechallenge.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.adapters.ListViewItemAdapter
import com.bresiu.codechallenge.model.PostWithUserAddress
import com.bresiu.codechallenge.presentation.uimodels.Result
import com.bresiu.codechallenge.presentation.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import javax.inject.Inject


class ItemListActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var listViewModel: ListViewModel
    private var twoPane: Boolean = false
    private lateinit var adapter: ListViewItemAdapter

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
        listViewModel.liveData.observe(this, Observer { uiModel ->
            updateUI(uiModel)
        })
    }

    private fun updateUI(uiModel: Result<List<PostWithUserAddress>>?) {
        when (uiModel?.state) {
            Result.LOADING -> onLoading()
            Result.ERROR -> onError(uiModel.error)
            Result.SUCCESS -> onSuccess(uiModel.bundle.unpack())
        }
    }

    private fun onSuccess(resultBundle: List<PostWithUserAddress>) {
        Log.d("BRS", "onSuccess " + resultBundle.size)
        adapter.addData(resultBundle)
    }

    private fun onError(error: Throwable?) {
        Log.d("BRS", "onError " + error?.message)
    }

    private fun onLoading() {
        Log.d("BRS", "onLoading")
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
        adapter = ListViewItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun initViewModel() {
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }
}
