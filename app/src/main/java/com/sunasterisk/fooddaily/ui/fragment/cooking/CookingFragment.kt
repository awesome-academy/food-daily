package com.sunasterisk.fooddaily.ui.fragment.cooking

import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.R

class CookingFragment: BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_cooking

    override fun initActionBar() {

    }

    override fun getArgument() {

    }

    companion object {
        fun newInstance(): CookingFragment = CookingFragment()
    }
}
