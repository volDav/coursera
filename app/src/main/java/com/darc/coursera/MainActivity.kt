package com.darc.coursera

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.darc.coursera.adapter.VPAdapter
import com.darc.coursera.base.BaseActivity
import com.darc.coursera.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.body_home_sesion.*

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        supportActionBar?.elevation = 2f

        Handler().postDelayed({
            loadFragments()
        },100)
    }

    private fun loadFragments() {
        val vpAdapter = VPAdapter(this)
        binding.viewPagerConsumo.offscreenPageLimit = vpAdapter.count
        binding.viewPagerConsumo.adapter = vpAdapter
        binding.tabLayoutConsumo.setupWithViewPager(binding.viewPagerConsumo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_tools,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> {}
            R.id.item2 -> AcercaDe.starActivity(this)
            R.id.item3 -> TopFivePets.startActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

}

