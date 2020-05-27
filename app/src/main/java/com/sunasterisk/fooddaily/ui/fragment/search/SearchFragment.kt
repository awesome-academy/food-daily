package com.sunasterisk.fooddaily.ui.fragment.search

import androidx.core.os.bundleOf
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.ui.base.BaseFragment

class SearchFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_search

    override fun initActionBar() {

    }

    override fun getArgument() {
        val searchKey = arguments?.getString(EXTRA_SEARCH_KEY)
    }

    companion object {
        private const val EXTRA_SEARCH_KEY = "com.sunasterisk.fooddaily.utils.Constants.EXTRA_SEARCH_KEY"
        fun newInstance(searchKey: String): SearchFragment = SearchFragment().apply {
            arguments = bundleOf(EXTRA_SEARCH_KEY to searchKey)
        }
    }
}
