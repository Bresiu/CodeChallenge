package com.bresiu.codechallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.model.AlbumItem
import com.bresiu.codechallenge.model.AlbumListItem
import com.bresiu.codechallenge.model.PhotoItem
import com.bresiu.codechallenge.presentation.views.HeaderItemDecoration.StickyHeaderInterface
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions


class AlbumListAdapter(private val glide: RequestManager) : ListAdapter<AlbumListItem, RecyclerView.ViewHolder>(AlbumListDiffCallback()), StickyHeaderInterface {
  object ViewType {
    const val HEADER_VIEW_TYPE = 0
    const val ITEM_VIEW_TYPE = 1
  }

  object ViewSize {
    const val MAIN = 1000
  }

  val requestOptions: RequestOptions = RequestOptions().override(ViewSize.MAIN).encodeQuality(50).placeholder(R.drawable.image_area)

  override fun bindHeaderData(header: TextView, headerPosition: Int) {
    val txt: String = getItem(headerPosition).toString()
    header.text = txt
  }

  override fun getHeaderLayout(headerPosition: Int): Int {
    return R.layout.album_header
  }

  override fun getHeaderPositionForItem(itemPosition: Int): Int {
    var i = itemPosition
    while (true) {
      if (isHeader(i)) return i
      i--
    }
  }

  override fun isHeader(itemPosition: Int): Boolean {
    return getItem(itemPosition)?.isHeader() ?: false
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
    return if (viewType == ViewType.ITEM_VIEW_TYPE) {
      ItemViewHolder(layoutInflater.inflate(R.layout.album_item, parent, false))
    } else {
      HeaderViewHolder(layoutInflater.inflate(R.layout.album_header, parent, false))
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (isHeader(position)) ViewType.HEADER_VIEW_TYPE else ViewType.ITEM_VIEW_TYPE
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val item = getItem(position)
    when (holder) {
      is ItemViewHolder -> holder.bind(item as PhotoItem)
      is HeaderViewHolder -> holder.bind(item as AlbumItem)
    }
  }

  inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view as ImageView
    fun bind(item: PhotoItem) {
      glide.load(item.url)
          .apply(requestOptions)
          .thumbnail(glide.load(item.thumbnailUrl))
          .into(imageView)
    }
  }

  inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view as TextView
    fun bind(item: AlbumItem) {
      textView.text = item.title
    }
  }
}
