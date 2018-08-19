package com.bresiu.codechallenge.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.databinding.PostContentBinding
import com.bresiu.codechallenge.model.PostWithUserAddress


class ListViewItemAdapter : RecyclerView.Adapter<ListViewItemAdapter.ViewHolder>() {

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
                R.id.post_content -> Log.d("BRS", "clicked at position: " + holder.layoutPosition + " post: $post")
            }
        }
    }
    //            val item = view.tag as PostWithUserAddress
//            if (twoPane) {
//                val fragment = ItemDetailFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ItemDetailFragment.ARG_ITEM_ID, item.postTitle)
//                    }
//                }
//                parentActivity.supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.item_detail_container, fragment)
//                        .commit()
//            } else {
//                val intent = Intent(view.context, ItemDetailActivity::class.java).apply {
//                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.postTitle)
//                }
//                view.context.startActivity(intent)
//            }
}