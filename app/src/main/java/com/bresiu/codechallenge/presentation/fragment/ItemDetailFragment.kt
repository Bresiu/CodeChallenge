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
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.databinding.ItemDetailBinding
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.viewmodel.DetailViewModel
import javax.inject.Inject

class ItemDetailFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("BRS", "onCreateView")
        return DataBindingUtil
                .inflate<ItemDetailBinding>(inflater, R.layout.item_detail, container, false)
                .apply { post = getItem(arguments) }
                .root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
        Log.d("BRS", "initViewModel")
        detailViewModel.getDetailUpdatesForUser(getItem(arguments).userId).observe(viewLifecycleOwner, Observer { albumWithPhotos ->
            albumWithPhotos.forEach { d("BRS", albumWithPhotos.toString()) }
        })
    }

    fun d(TAG: String, message: String) {
        val maxLogSize = 2000
        for (i in 0..message.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > message.length) message.length else end
            android.util.Log.d(TAG, message.substring(start, end))
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
