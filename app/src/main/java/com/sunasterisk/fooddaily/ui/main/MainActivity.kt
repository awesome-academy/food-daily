package com.sunasterisk.fooddaily.ui.main

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.base.BaseActivity
import com.sunasterisk.fooddaily.ui.fragment.home.HomeFragment
import com.sunasterisk.fooddaily.ui.communicator.OnFragmentInteractionListener
import com.sunasterisk.fooddaily.ui.fragment.collection.CollectionFragment
import com.sunasterisk.fooddaily.ui.fragment.cooking.CookingFragment
import com.sunasterisk.fooddaily.ui.fragment.search.SearchFragment
import com.sunasterisk.fooddaily.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.search_view.*

class MainActivity :
    BaseActivity(),
    OnFragmentInteractionListener,
    View.OnClickListener,
    SearchView.OnQueryTextListener {

    override val layoutRes: Int = R.layout.activity_main

    private var isShowFrameSearch = false
    private lateinit var foodsAll: List<FoodDetail>
    private val bottomNavSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchScreen(item, HomeFragment.newInstance(foodsAll))
                true
            }
            R.id.navigation_collection -> {
                switchScreen(item, CollectionFragment.newInstance())
                true
            }
            R.id.navigation_cooking -> {
                switchScreen(item, CookingFragment.newInstance())
                true
            }
            else -> false
        }
    }

    override fun initView() {
        initToolbar()
        createFoodsAll()
        setFragment(HomeFragment.newInstance(foodsAll))
        initBottomNavigation()
        initListeners()
    }

    override fun initPresenter() {}

    override fun updateActionbarTitle(actionBarTitle: Int) {
        textActionBarTitle.text = resources.getString(actionBarTitle)
    }

    override fun setDisplayButtonBackActionBar(buttonBackActionBarVisibility: Int) {
        buttonBackActionBar.visibility = buttonBackActionBarVisibility
    }

    override fun setDisplayButtonSearchActionBar(buttonSearchActionBarVisibility: Int) {
        buttonSearchActionBar.visibility = buttonSearchActionBarVisibility
    }

    override fun setDisplayButtonCollectionActionBar(buttonCollectionActionBarVisibility: Int) {
        buttonCollectionActionBar.visibility = buttonCollectionActionBarVisibility
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            setFragment(SearchFragment.newInstance(it))
        }
        controlFrameSearch()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSearchActionBar -> controlFrameSearch()
            R.id.frameSearch -> controlFrameSearch()
            R.id.buttonBackActionBar -> backToFragmentHome()
        }
    }

    private fun backToFragmentHome() {
        setFragment(HomeFragment.newInstance(foodsAll))
    }


    private fun initToolbar() {
        setSupportActionBar(actionbarMain as Toolbar?)
        (actionbarMain as Toolbar?)?.setContentInsetsAbsolute(0, 0)
    }

    private fun initListeners() {
        buttonSearchActionBar.setOnClickListener(this)
        buttonBackActionBar.setOnClickListener(this)
    }

    private fun createFoodsAll() {
        foodsAll = intent.getParcelableArrayListExtra(Constants.EXTRA_FOOD_LIST)
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavSelected)
    }

    private fun switchScreen(menuItem: MenuItem, fragment: Fragment) {
        textActionBarTitle.text = menuItem.title
        setFragment(fragment)
    }


    private fun controlFrameSearch() {
        (isShowFrameSearch)  = if (isShowFrameSearch) {
            hideFrameSearch()
            false
        } else {
            showFrameSearch()
            true
        }
    }

    private fun showFrameSearch() {
        searchViewHomeScreen.setOnQueryTextListener(this)
        frameSearch.visibility = View.VISIBLE
        frameSearch.setOnClickListener(this)
    }

    private fun hideFrameSearch() {
        searchViewHomeScreen.setQuery(null, false)
        frameSearch.visibility = View.GONE
    }

    companion object {
        fun getIntent(context: Context, foods: List<FoodDetail>): Intent =
            Intent(context, MainActivity::class.java)
                .putParcelableArrayListExtra(
                    Constants.EXTRA_FOOD_LIST,
                    foods as ArrayList<out Parcelable>
                )
    }
}
