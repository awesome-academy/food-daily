package com.sunasterisk.fooddaily.ui.fragment.search

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.adapter.FoodAdapter
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.utils.FoodType
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.frame_loading.*

private const val CHARACTER_SPACE = " "
private const val CHARACTER_APOSTROPHE = "'"

class SearchFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_search
    private var searchKey: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        val animRotate = AnimationUtils.loadAnimation(activity, R.anim.anim_rotate)
        imageLoadSearchResult.startAnimation(animRotate)
    }

    override fun initActionBar() {
        textActionBarTitle.text = getString(R.string.title_search)
        buttonSearchActionBar.visibility = View.GONE
        buttonCollectionActionBar.visibility = View.GONE
    }

    override fun getArgument() {
        searchKey = arguments?.getString(ARGUMENT_SEARCH_KEY)
    }

    override fun initPresenter() {
        val list = mutableListOf<FoodDetail>().apply {
            for (i in 0..10) add(
                FoodDetail(
                    null,
                    "food $i",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        }
        textNotifySearchResult.text = StringBuilder().apply {
            append(getString(R.string.title_search_result))
            append(CHARACTER_SPACE)
            append(CHARACTER_APOSTROPHE)
            append(searchKey)
            append(CHARACTER_APOSTROPHE)
        }.toString()
        val adapterSearch = FoodAdapter(FoodType.SEARCH_RESULT, ::onClickListener)
        recyclerViewSearchResult.adapter = adapterSearch
        adapterSearch.updateData(list)
    }

    private fun onClickListener(foodDetail: FoodDetail) {

    }

    private fun initListener() {
        buttonBackActionBar.setOnClickListener {
            removeFragment(this)
        }
    }

    companion object {
        private const val ARGUMENT_SEARCH_KEY = "ARGUMENT_SEARCH_KEY"
        fun newInstance(searchKey: String): SearchFragment = SearchFragment().apply {
            arguments = bundleOf(ARGUMENT_SEARCH_KEY to searchKey)
        }
    }
}
