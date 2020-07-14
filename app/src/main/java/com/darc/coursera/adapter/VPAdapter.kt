package com.darc.coursera.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.darc.coursera.MainActivity
import com.darc.coursera.fragments.DetailFragment
import com.darc.coursera.fragments.ListFragment

class VPAdapter(val activity: MainActivity) : FragmentPagerAdapter(activity.supportFragmentManager) {

    private val titulos = ArrayList<String>(2)

    init {
        titulos.add("Lista")
        titulos.add("Detalle")
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> ListFragment.newFragment()
            1 -> DetailFragment.newFragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return titulos.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titulos[position]
    }
}