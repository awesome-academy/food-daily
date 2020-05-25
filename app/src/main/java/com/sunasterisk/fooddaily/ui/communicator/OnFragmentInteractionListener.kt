package com.sunasterisk.fooddaily.ui.communicator

interface OnFragmentInteractionListener {
    fun updateActionbarTitle(actionBarTitle: Int)
    fun setDisplayButtonBackActionBar(buttonBackActionBarVisibility: Int)
    fun setDisplayButtonSearchActionBar(buttonSearchActionBarVisibility: Int)
    fun setDisplayButtonCollectionActionBar(buttonCollectionActionBarVisibility: Int)
}
