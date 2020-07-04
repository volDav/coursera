package com.darc.coursera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.darc.coursera.databinding.ActivityMainBinding
import com.darc.coursera.ktx.bindLazy
import kotlinx.android.synthetic.main.body_home_sesion.*

class MainActivity : AppCompatActivity() {


    private val toolbar: Toolbar by bindLazy(R.id.toolbar)

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.clase = this

        setSupportActionBar(toolbar)


    }
}