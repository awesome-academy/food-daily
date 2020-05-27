package com.sunasterisk.fooddaily.ui.fragment.collection

import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.R

class CollectionFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_collection

    override fun initActionBar() {

    }

    override fun getArgument() {

    }

    companion object {
        fun newInstance(): CollectionFragment = CollectionFragment()
    }
}
