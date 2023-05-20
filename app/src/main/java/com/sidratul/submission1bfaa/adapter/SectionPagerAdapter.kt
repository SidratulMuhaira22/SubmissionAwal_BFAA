package com.sidratul.submission1bfaa.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sidratul.submission1bfaa.main.FollowersFragment
import com.sidratul.submission1bfaa.main.FollowingFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }


}