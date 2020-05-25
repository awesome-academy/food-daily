package com.sunasterisk.fooddaily.ui.fragment.search

import android.os.Bundle
import android.view.View
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.utils.Constants

class SearchFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_search
    override val actionBarTitle: Int = R.string.title_search
    override val buttonBackActionBarVisibility: Int = View.VISIBLE
    override val buttonSearchActionBarVisibility: Int = View.GONE
    override val buttonCollectionActionBarVisibility: Int = View.GONE

    override fun getArgument() {
        val searchKey = arguments?.getString(Constants.EXTRA_SEARCH_KEY)
    }

    companion object {
        fun newInstance(searchKey: String): SearchFragment = SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.EXTRA_SEARCH_KEY, searchKey)
                }
            }
    }
}
