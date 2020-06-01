package com.sunasterisk.fooddaily.ui.activity.food

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.viewpager.widget.ViewPager
import com.sunasterisk.fooddaily.R
import com.sunasterisk.fooddaily.data.model.FoodDetail
import com.sunasterisk.fooddaily.ui.adapter.InstructionAdapter
import com.sunasterisk.fooddaily.ui.base.BaseActivity
import com.sunasterisk.fooddaily.ui.fragment.instruction.InstructionFragment
import com.sunasterisk.fooddaily.utils.applyGlide
import kotlinx.android.synthetic.main.activity_food_detail.*
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.dialog_choose_collection.*
import kotlinx.android.synthetic.main.dialog_cogratulations.*
import kotlinx.android.synthetic.main.frame_food_detail_instruction.*
import kotlinx.android.synthetic.main.frame_food_detail_preview.*

class FoodDetailActivity :
    BaseActivity(),
    View.OnClickListener,
    ViewPager.OnPageChangeListener {

    override val layoutRes: Int = R.layout.activity_food_detail

    private var food: FoodDetail? = null
    private var isShowFrameInstruction = false
    private var currentPositionPage = 0
    private var instructionAdapter: InstructionAdapter? = null

    override fun initPresenter() {}

    override fun initView() {
        food = intent.getParcelableExtra(EXTRA_FOOD_ITEM)
        food?.let { showFoodDetail(it) }
        initActionBar()
        initListener()
    }

    private fun initListener() {
        buttonCollectionActionBar.setOnClickListener(this)
        buttonBackActionBar.setOnClickListener(this)
        buttonStart.setOnClickListener(this)
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
        imageFoodDetail.applyGlide(food.imageUrl)
    }

    private fun initActionBar() {
        buttonSearchActionBar.visibility = View.GONE
        textActionBarTitle.text = getString(R.string.title_food_detail)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonBackActionBar -> backToHomeScreen()
            R.id.buttonCollectionActionBar -> showDialogChooseCollection()
            R.id.buttonStart -> controlDisplayInstruction()
            R.id.buttonCompleted -> showDialogCongratulations()
            R.id.buttonPreviousNavigation -> goToPreviousPage()
            R.id.buttonNextNavigation -> goToNextPage()
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        currentPositionPage = position
        when (position) {
            0 -> {
                hideButtonPrevious()
                displayButtonNext()
            }
            pagerInstruction.adapter?.count?.minus(1) -> {
                displayButtonCompleted()
                hideButtonNext()
                displayButtonPrevious()
            }
            else -> displayButtonNav()
        }
    }

    private fun goToNextPage() {
        pagerInstruction.adapter?.let {
            if (currentPositionPage.plus(1) < it.count) {
                pagerInstruction.currentItem = currentPositionPage.plus(1)
            }
        }
    }

    private fun goToPreviousPage() {
        pagerInstruction.adapter?.let {
            if (currentPositionPage.minus(1) >= 0) {
                pagerInstruction.currentItem = currentPositionPage.minus(1)
            }
        }
    }

    private fun showDialogCongratulations() {
        Dialog(this, R.style.DialogTheme).run {
            setCancelable(false)
            setContentView(R.layout.dialog_cogratulations)
            show()
            buttonNoThank.setOnClickListener { dismiss() }
            buttonShareOnFacebook.setOnClickListener {
                shareRecipeOnFacebook()
                dismiss()
            }
        }
    }

    private fun shareRecipeOnFacebook() {
        //TODO share recipe on facebook
    }

    private fun showDialogChooseCollection() {
        Dialog(this, R.style.DialogTheme).run {
            setCancelable(false)
            setContentView(R.layout.dialog_choose_collection)
            show()
            buttonCloseChooseDialog.setOnClickListener { dismiss() }
            buttonFavorite.setOnClickListener {
                addFoodToFavorite()
                dismiss()
            }
            buttonFamily.setOnClickListener {
                addFoodToFamily()
                dismiss()
            }
            buttonParty.setOnClickListener {
                addFoodToParty()
                dismiss()
            }
        }
    }

    private fun backToHomeScreen() = finish()

    private fun controlDisplayInstruction() {
        isShowFrameInstruction = if (isShowFrameInstruction) {
            showFramePreview()
            false
        } else {
            showFrameInstruction()
            true
        }
    }

    private fun showFramePreview() {
        buttonStart.visibility = View.VISIBLE
        frameFoodDetailPreview.visibility = View.VISIBLE
        frameFoodDetailInstruction.visibility = View.GONE
    }

    private fun showFrameInstruction() {
        buttonStart.visibility = View.GONE
        frameFoodDetailPreview.visibility = View.GONE
        frameFoodDetailInstruction.visibility = View.VISIBLE
        initInstructionAdapter()
        buttonPreviousNavigation.setOnClickListener(this)
        buttonNextNavigation.setOnClickListener(this)
    }

    private fun initInstructionAdapter() {

        val fragmentSteps = food?.instructions?.map {
            InstructionFragment.newInstance(it)
        }
        instructionAdapter = InstructionAdapter(supportFragmentManager).apply {
            fragmentSteps?.let { addFragment(it) }
        }
        pagerInstruction.apply {
            adapter = instructionAdapter
            addOnPageChangeListener(this@FoodDetailActivity)
        }
    }

    private fun addFoodToParty() {
        //TODO add food to party collection
    }

    private fun addFoodToFamily() {
        //TODO add food to family collection
    }

    private fun addFoodToFavorite() {
        //TODO add food to favorite collection
    }

    private fun displayButtonNav() {
        buttonCompleted.visibility = View.GONE
        buttonPreviousNavigation.visibility = View.VISIBLE
        buttonNextNavigation.visibility = View.VISIBLE
    }

    private fun hideButtonNext() {
        buttonNextNavigation.visibility = View.GONE
    }

    private fun hideButtonPrevious() {
        buttonPreviousNavigation.visibility = View.GONE
    }

    private fun displayButtonNext() {
        buttonNextNavigation.visibility = View.VISIBLE
    }

    private fun displayButtonPrevious() {
        buttonPreviousNavigation.visibility = View.VISIBLE
    }

    private fun displayButtonCompleted() {
        buttonCompleted.visibility = View.VISIBLE
        buttonCompleted.setOnClickListener(this)
    }

    companion object {

        private const val EXTRA_FOOD_ITEM =
            "com.sunasterisk.fooddaily.utils.Constants.EXTRA_FOOD_ITEM"

        fun getIntent(context: Context, food: FoodDetail): Intent =
            Intent(context, FoodDetailActivity::class.java)
                .putExtra(EXTRA_FOOD_ITEM, food)
    }
}
