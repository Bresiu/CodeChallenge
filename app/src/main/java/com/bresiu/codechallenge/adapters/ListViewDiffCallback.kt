package com.bresiu.codechallenge.adapters

import androidx.recyclerview.widget.DiffUtil
import com.bresiu.codechallenge.model.PostWithUser

class ListViewDiffCallback : DiffUtil.ItemCallback<PostWithUser>() {
    override fun areItemsTheSame(oldItem: PostWithUser, newItem: PostWithUser): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: PostWithUser, newItem: PostWithUser): Boolean {
        return oldItem == newItem
    }

}