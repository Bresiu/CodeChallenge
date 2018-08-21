package com.bresiu.codechallenge.adapters

import androidx.recyclerview.widget.DiffUtil
import com.bresiu.codechallenge.model.PostWithUserAddress

class ListViewDiffCallback : DiffUtil.ItemCallback<PostWithUserAddress>() {
    override fun areItemsTheSame(oldItem: PostWithUserAddress, newItem: PostWithUserAddress): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: PostWithUserAddress, newItem: PostWithUserAddress): Boolean {
        return oldItem == newItem
    }

}