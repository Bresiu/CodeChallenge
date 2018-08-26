package com.bresiu.codechallenge.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.bresiu.codechallenge.R
import com.bresiu.codechallenge.model.PostWithUser
import com.bresiu.codechallenge.presentation.fragment.ItemDetailFragment
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_item_detail)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    if (savedInstanceState == null) {
      intent.extras?.let {
        supportFragmentManager.beginTransaction()
            .add(R.id.item_detail_container, ItemDetailFragment.newInstance(getItem(intent.extras!!)))
            .commit()

      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) =
      when (item.itemId) {
        android.R.id.home -> {
          navigateUpTo(Intent(this, ItemListActivity::class.java))
          true
        }
        else -> super.onOptionsItemSelected(item)
      }

  companion object {
    private const val ARG_ITEM = "item"
    fun newStartIntent(context: Context, item: PostWithUser): Intent {
      return Intent(context, ItemDetailActivity::class.java).apply {
        putExtra(ARG_ITEM, item)
      }
    }

    fun getItem(arguments: Bundle): PostWithUser {
      return arguments.getParcelable(ARG_ITEM)
          ?: throw IllegalStateException("PostWithUser argument is missing")
    }
  }
}
