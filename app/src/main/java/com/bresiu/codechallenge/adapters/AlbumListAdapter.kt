package com.bresiu.codechallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.data.entity.AlbumWithPhotos
import com.bresiu.codechallenge.presentation.views.HeaderItemDecoration.StickyHeaderInterface
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import java.util.function.Consumer

class AlbumListAdapter(private val glide: RequestManager) : PagedListAdapter<AlbumWithPhotos, AlbumListAdapter.HeaderViewHolder>(AlbumListDiffCallback()), StickyHeaderInterface {
  object ViewSize {
    const val MAIN = 1000
  }

  val requestOptions: RequestOptions = RequestOptions().override(ViewSize.MAIN).encodeQuality(50).placeholder(R.drawable.image_area)

  override fun bindHeaderData(header: TextView, headerPosition: Int) {
    header.text = getItem(headerPosition)?.title
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
    return true
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
    val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
    return HeaderViewHolder(layoutInflater.inflate(R.layout.album_item, parent, false), layoutInflater.inflate(R.layout.photo_item, parent, false) as ImageView)
  }

  override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
    holder.bind(getItem(position))
//
//    when (holder) {
//      is ItemViewHolder -> holder.bind(item as PhotoItem)
//      is HeaderViewHolder -> holder.bind(item as AlbumItem)
//    }
  }
//
//  inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    private val imageView = view as ImageView
//    fun bind(item: PhotoItem) {
//      glide.load(item.url)
//          .apply(requestOptions)
//          .thumbnail(glide.load(item.thumbnailUrl))
//          .into(imageView)
//    }
//  }

  inner class HeaderViewHolder(private val albumView: View, private val photoView: ImageView) : RecyclerView.ViewHolder(albumView) {

    fun bind(item: AlbumWithPhotos?) {
      val albumTitle: TextView = albumView.findViewById(R.id.album_header)
      albumTitle.text = item?.title
      val photosContainer: LinearLayout = albumView.findViewById(R.id.photos_container)
      item?.photos?.forEach(Consumer {
        photosContainer.addView(photoView)
        glide.load(it.url)
            .apply(requestOptions)
            .thumbnail(glide.load(it.thumbnailUrl))
            .into(photoView)
      })
    }
  }
}
