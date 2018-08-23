package com.bresiu.codechallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.databinding.PostContentBinding
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.activity.ItemListActivity


class ListViewItemAdapter(private val parentActivity: ItemListActivity) : ListAdapter<PostWithUser, ListViewItemAdapter.ViewHolder>(ListViewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.post_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(createOnClickListener(item), item)
            itemView.tag = item
        }
    }

    private fun createOnClickListener(post: PostWithUser): View.OnClickListener {
        return View.OnClickListener {
            when (it.id) {
                R.id.delete -> parentActivity.deletePostById(post.postId)
                R.id.post_content -> parentActivity.navigateToDetails(post)
            }
        }
    }

    inner class ViewHolder(private val binding: PostContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, postWithUser: PostWithUser) {
            binding.apply {
                clickListener = listener
                post = postWithUser
                executePendingBindings()
            }
        }
    }
}