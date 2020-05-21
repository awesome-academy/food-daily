package com.sunasterisk.fooddaily.ui.main

import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.ui.base.BaseActivity
import com.sunasterisk.fooddaily.ui.fragment.HomeFragment

class MainActivity : BaseActivity() {

    override val layoutRes: Int = R.layout.activity_main

    override fun initView() {
        initFragment()
    }

    override fun initPresenter() {
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameContent, HomeFragment())
            .commit()
    }
}
