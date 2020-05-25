package com.sunasterisk.fooddaily.ui.fragment.cooking

import android.view.View
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.R

class CookingFragment: BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_cooking
    override val actionBarTitle: Int = R.string.title_cooking
    override val buttonBackActionBarVisibility: Int = View.GONE
    override val buttonSearchActionBarVisibility: Int = View.GONE
    override val buttonCollectionActionBarVisibility: Int = View.GONE

    override fun getArgument() {

    }

    companion object {
        fun newInstance(): CookingFragment = CookingFragment()
    }
}
