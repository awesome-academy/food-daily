package com.sunasterisk.fooddaily.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getArgument()
        return inflater.inflate(layoutRes, container, false)
    }

    abstract fun getArgument()
}
