package com.bresiu.codechallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.adapters.AlbumListAdapter.ViewHolder
import com.bresiu.codechallenge.model.AlbumListItem
import com.bresiu.codechallenge.presentation.views.HeaderItemDecoration.StickyHeaderInterface

class AlbumListAdapter : ListAdapter<AlbumListItem, ViewHolder>(
    AlbumListDiffCallback()), StickyHeaderInterface {
  override fun bindHeaderData(header: TextView, headerPosition: Int) {
    header.text = getItem(headerPosition).toString()
  }

  override fun getHeaderLayout(headerPosition: Int): Int {
    return R.layout.header_view
  }

  override fun getHeaderPositionForItem(itemPosition: Int): Int {
    var headerPosition = 0
    var position = itemPosition
    do {
      if (this.isHeader(itemPosition)) {
        headerPosition = itemPosition
        break
      }
      position -= 1
    } while (position >= 0)
    return headerPosition
  }

  override fun isHeader(itemPosition: Int): Boolean {
    return getItem(itemPosition).isHeader()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.album_container, parent, false))
//    return ViewHolder(DataBindingUtil.inflate(
//        LayoutInflater.from(parent.context),
//        R.layout.album_container, parent, false)
//    )
  }


  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  }

//  private fun createOnClickListener(post: AlbumListItem): View.OnClickListener {
//    return View.OnClickListener {
//      when (it.id) {
////        R.id.delete -> parentActivity.deletePostById(post.postId)
////        R.id.post_content -> parentActivity.navigateToDetails(post)
//      }
//    }
//  }

  inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
//    fun bind(listener: View.OnClickListener, postWithUser: PostWithUser) {
//      binding.apply {
//        clickListener = listener
//        post = postWithUser
//        executePendingBindings()
//      }
//    }
  }
}
