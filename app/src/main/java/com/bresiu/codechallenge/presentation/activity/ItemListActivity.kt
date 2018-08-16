package com.bresiu.codechallenge.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.adapters.SimpleItemRecyclerViewAdapter
import com.bresiu.codechallenge.dummy.DummyContent
import com.bresiu.codechallenge.viewmodels.ListViewModel
import com.bresiu.codechallenge.viewmodels.uimodels.Result
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import javax.inject.Inject

class ItemListActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var listViewModel: ListViewModel
    private var twoPane: Boolean = false

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

    private fun updateUI(uiModel: Result?) {

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
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
    }

    private fun initViewModel() {
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }
}
