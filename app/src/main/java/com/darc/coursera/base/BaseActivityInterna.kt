package com.darc.coursera.base

import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.darc.coursera.R

open class BaseActivityInterna : BaseActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        setHomeIndicator(R.drawable.ic_back_arrow)
    }
}