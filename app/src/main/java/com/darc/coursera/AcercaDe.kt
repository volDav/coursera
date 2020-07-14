package com.darc.coursera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.darc.coursera.base.BaseActivity
import com.darc.coursera.base.BaseActivityInterna

class AcercaDe : BaseActivityInterna() {


    companion object {
        fun starActivity(context: Context){
            val intent = Intent(context,AcercaDe::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca_de)


    }
}