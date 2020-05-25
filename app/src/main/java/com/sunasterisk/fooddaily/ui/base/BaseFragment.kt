package com.sunasterisk.fooddaily.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.ui.communicator.OnFragmentInteractionListener

abstract class BaseFragment: Fragment() {

    protected abstract val layoutRes: Int
    protected abstract val actionBarTitle: Int
    protected abstract val buttonBackActionBarVisibility: Int
    protected abstract val buttonSearchActionBarVisibility: Int
    protected abstract val buttonCollectionActionBarVisibility: Int

    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getArgument()
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            onFragmentInteractionListener = context
        }
        updateActionBar()
    }

    override fun onDetach() {
        super.onDetach()
        onFragmentInteractionListener = null
    }

    abstract fun getArgument()

    fun switchFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frameContent, fragment)
            ?.commit()
    }

    private fun updateActionBar() {
        onFragmentInteractionListener?.apply {
            updateActionbarTitle(actionBarTitle)
            setDisplayButtonBackActionBar(buttonBackActionBarVisibility)
            setDisplayButtonSearchActionBar(buttonSearchActionBarVisibility)
            setDisplayButtonCollectionActionBar(buttonCollectionActionBarVisibility)
        }
    }
}
