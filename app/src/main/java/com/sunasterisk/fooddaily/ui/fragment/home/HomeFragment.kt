package com.sunasterisk.fooddaily.ui.fragment.home

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.adapter.FoodAdapter
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.ui.fragment.food_detail.FoodDetailFragment
import com.sunasterisk.fooddaily.ui.fragment.search.SearchFragment
import com.sunasterisk.fooddaily.utils.Constants
import com.sunasterisk.fooddaily.utils.FoodType
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.search_view.*
import java.util.*

const val COLUMN_LIMIT = 2

class HomeFragment:
    BaseFragment(),
    View.OnClickListener,
    HomeContract.View,
    SearchView.OnQueryTextListener
{

    override val layoutRes: Int = R.layout.fragment_home

    private val homePresenter = HomePresenter(this)
    private var otherFoods: List<FoodDetail>? = null
    private var isSortList: Boolean = false
    private var isShowFrameSearch = false

    override fun initActionBar() {
        textActionBarTitle.text = getString(R.string.title_home)
        buttonSearchActionBar.setOnClickListener(this)
        buttonBackActionBar.visibility = View.GONE
        buttonCollectionActionBar.visibility = View.GONE
    }

    override fun getArgument() {
        otherFoods =
            arguments?.getParcelableArrayList<FoodDetail>(Constants.EXTRA_FOOD_LIST) as ArrayList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otherFoods?.let { homePresenter.createDailyMenu(it) }
        displayOtherFoodList()
        buttonSortOtherFood.setOnClickListener(this)
    }

    override fun showDailyMenu(dailyFoods: List<FoodDetail>) {
        recyclerViewDailyMenu.apply {
            adapter =
                FoodAdapter(dailyFoods, FoodType.FOOD_TYPE_DAILY_MENU) { food ->
                    onFoodItemClick(food)
                }
            setHasFixedSize(false)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            switchFragment(SearchFragment.newInstance(it))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSortOtherFood -> displayOtherFood()
            R.id.buttonSearchActionBar -> controlDisplayFrameSearch()
            R.id.frameSearch -> hideFrameSearch()
        }
    }

    private fun displayOtherFood() {
        val icon = if (isSortList) {
            displayOtherFoodGrid()
            R.drawable.ic_list
        } else {
            displayOtherFoodList()
            R.drawable.ic_grid
        }
        buttonSortOtherFood.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
    }

    private fun displayOtherFoodList() {
        recyclerViewOtherFood.apply {
            adapter = otherFoods?.let {
                FoodAdapter(it, FoodType.FOOD_TYPE_OTHER_LIST) { food ->
                    onFoodItemClick(food)
                }
            }
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(false)
        }
        isSortList = true
    }

    private fun displayOtherFoodGrid() {
        recyclerViewOtherFood.apply {
            adapter =
                otherFoods?.let {
                    FoodAdapter(it, FoodType.FOOD_TYPE_OTHER_GRID) { food ->
                        onFoodItemClick(food)
                    }
                }
            layoutManager = GridLayoutManager(context, COLUMN_LIMIT)
        }
        isSortList = false
    }

    private fun onFoodItemClick(food: FoodDetail) {
        switchFragment(FoodDetailFragment.newInstance(food))
    }

    private fun controlDisplayFrameSearch() {
        isShowFrameSearch = if (isShowFrameSearch) {
            hideFrameSearch()
            false
        } else {
            displayFrameSearch()
            true
        }
    }

    private fun hideFrameSearch() {
        searchViewHomeScreen.setQuery(null, false)
        frameSearch.visibility = View.GONE
    }

    private fun displayFrameSearch() {
        frameSearch.visibility = View.VISIBLE
        frameSearch.setOnClickListener(this)
        searchViewHomeScreen.setOnQueryTextListener(this)
    }

    companion object {
        fun newInstance(foods: List<FoodDetail>): HomeFragment = HomeFragment().apply {
                arguments = bundleOf().apply {
                    putParcelableArrayList(
                        Constants.EXTRA_FOOD_LIST,
                        foods as ArrayList<out Parcelable>
                    )
                }
            }
    }
}
