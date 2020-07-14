package com.darc.coursera.base

import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.darc.coursera.AcercaDe
import com.darc.coursera.R
import com.darc.coursera.TopFivePets

open class BaseActivity :  AppCompatActivity() {


    fun setHomeIndicator(@DrawableRes homeIndicator: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val vectorDrawableCompat = VectorDrawableCompat.create(resources, homeIndicator, null)
        vectorDrawableCompat?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(vectorDrawableCompat)
    }


}