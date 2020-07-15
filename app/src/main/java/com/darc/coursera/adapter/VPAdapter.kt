package com.darc.coursera.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.darc.coursera.MainActivity
import com.darc.coursera.fragments.DetailFragment
import com.darc.coursera.fragments.ListFragment

class VPAdapter(activity: MainActivity) : FragmentPagerAdapter(activity.supportFragmentManager) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> ListFragment.newFragment()
            1 -> DetailFragment.newFragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int = 2

}