package com.sunasterisk.fooddaily.ui.fragment.collection

import android.view.View
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.R

class CollectionFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_collection
    override val actionBarTitle: Int = R.string.title_collection
    override val buttonBackActionBarVisibility: Int = View.GONE
    override val buttonSearchActionBarVisibility: Int = View.GONE
    override val buttonCollectionActionBarVisibility: Int = View.GONE

    override fun getArgument() {

    }

    companion object {
        fun newInstance(): CollectionFragment = CollectionFragment()
    }
}
