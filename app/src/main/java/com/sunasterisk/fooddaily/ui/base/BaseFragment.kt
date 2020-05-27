package com.sunasterisk.fooddaily.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunasterisk.fooddaily.R

abstract class BaseFragment: Fragment() {

    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getArgument()
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
    }

    abstract fun initActionBar()
    abstract fun getArgument()

    fun switchFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frameContent, fragment)
            ?.commit()
    }
}
