package com.bresiu.codechallenge.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.model.PostWithUserAddress
import com.bresiu.codechallenge.presentation.activity.ItemDetailActivity
import com.bresiu.codechallenge.presentation.activity.ItemListActivity
import com.bresiu.codechallenge.presentation.fragment.ItemDetailFragment
import kotlinx.android.synthetic.main.post_content.view.*


class ListViewItemAdapter(private val parentActivity: ItemListActivity,
                          private val twoPane: Boolean) :
        RecyclerView.Adapter<ListViewItemAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private val values: MutableList<PostWithUserAddress>

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as PostWithUserAddress
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ItemDetailFragment.ARG_ITEM_ID, item.postTitle)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.postTitle)
                }
                v.context.startActivity(intent)
            }
        }
        values = arrayListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.postTitle.text = item.postTitle
        holder.userAddress.text = item.userEmail

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    fun addData(data: List<PostWithUserAddress>) {
        values.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postTitle: TextView = view.post_title
        val userAddress: TextView = view.user_address
    }
}