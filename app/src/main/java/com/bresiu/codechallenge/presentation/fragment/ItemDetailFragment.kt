package com.bresiu.codechallenge.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.databinding.ItemDetailBinding
import com.bresiu.codechallenge.model.PostWithUserAddress
import com.bresiu.codechallenge.presentation.viewmodel.DetailViewModel
import javax.inject.Inject

class ItemDetailFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
        Log.d("BRS", "text: " + detailViewModel.text)
    }

    companion object {
        private const val ARG_ITEM = "item"
        fun newInstance(item: PostWithUserAddress): ItemDetailFragment {
            val bundle = Bundle().apply { putParcelable(ARG_ITEM, item) }
            return ItemDetailFragment().apply { arguments = bundle }
        }

        fun getItem(arguments: Bundle?): PostWithUserAddress {
            return arguments?.getParcelable(ARG_ITEM)
                    ?: throw IllegalStateException("PostWithUserAddress argument is missing")
        }
    }
}
