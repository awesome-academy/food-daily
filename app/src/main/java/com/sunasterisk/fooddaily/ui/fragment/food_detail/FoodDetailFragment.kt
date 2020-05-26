package com.sunasterisk.fooddaily.ui.fragment.food_detail

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.base.BaseFragment
import com.sunasterisk.fooddaily.utils.applyGlide
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.fragment_food_detail.*

const val EXTRA_FOOD_ITEM = "com.sunasterisk.fooddaily.utils.Constants.EXTRA_FOOD_ITEM"

class FoodDetailFragment : BaseFragment(), View.OnClickListener {

    override val layoutRes: Int = R.layout.fragment_food_detail

    override fun initActionBar() {
        textActionBarTitle.text = getString(R.string.title_food_detail)
        buttonBackActionBar.visibility = View.VISIBLE
        buttonCollectionActionBar.visibility = View.VISIBLE
        buttonSearchActionBar.visibility = View.GONE
    }

    private var food: FoodDetail? = null

    override fun getArgument() {
        food = arguments?.getParcelable<FoodDetail>(EXTRA_FOOD_ITEM) as FoodDetail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        food?.let { showFoodDetail(it) }
        buttonCollectionActionBar.setOnClickListener(this)
        buttonBackActionBar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonCollectionActionBar -> showDialogChooseCollection()
        }
    }

    private fun showDialogChooseCollection() {
        val context = context ?: return
        Dialog(context, R.style.DialogTheme).run {
            setCancelable(false)
            setContentView(R.layout.dialog_choose_collection)
            show()
        }
    }

    private fun showFoodDetail(food: FoodDetail) {
        textFoodNameDes.text = food.title
        textFoodNameDes.isSelected = true
        textSummaryDes.text = HtmlCompat.fromHtml(
            food.summary.toString(),
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        textReadyMinutesDes.text = food.getRequiredTime()
        textPriceDes.text = food.getPriceEstimate()
        textIngredientDes.text = food.getIngredient() ?: getString(R.string.title_updating)
        context?.let { imageFoodDetail.applyGlide(food.imageUrl) }
    }

    companion object {
        fun newInstance(food: FoodDetail): FoodDetailFragment = FoodDetailFragment().apply {
            arguments = bundleOf(EXTRA_FOOD_ITEM to food)
        }
    }
}
