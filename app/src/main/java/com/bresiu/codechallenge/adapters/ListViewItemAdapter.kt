package com.bresiu.codechallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.databinding.PostContentBinding
import com.bresiu.codechallenge.model.PostWithUserAddress
import com.bresiu.codechallenge.presentation.activity.ItemListActivity


class ListViewItemAdapter(private val parentActivity: ItemListActivity) : RecyclerView.Adapter<ListViewItemAdapter.ViewHolder>() {
    private val data: MutableList<PostWithUserAddress> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.post_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.apply {
            bind(createOnClickListener(item, holder), item)
            itemView.tag = item
        }
    }

    override fun getItemCount() = data.size

    fun addData(data: List<PostWithUserAddress>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private fun deleteItemAtPosition(position: Int) {
        parentActivity.deletePostById(data[position].postId)
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(private val binding: PostContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, postWithUserAddress: PostWithUserAddress) {
            binding.apply {
                clickListener = listener
                post = postWithUserAddress
                executePendingBindings()
            }
        }
    }

    private fun createOnClickListener(post: PostWithUserAddress, holder: ViewHolder): View.OnClickListener {
        return View.OnClickListener {
            when (it.id) {
                R.id.delete -> deleteItemAtPosition(holder.layoutPosition)
                R.id.post_content -> parentActivity.navigateToDetails(post)
            }
        }
    }
}