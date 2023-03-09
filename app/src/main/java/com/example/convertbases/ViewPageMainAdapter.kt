package com.example.convertbases

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageMainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        //return number for fragment
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return FragmentDescription_1()
            }
            1 -> {
                return FragmentDescription_2()
            }
            2 -> {
                return FragmentHome()
            }
            else -> {
                return Fragment()
            }
        }
    }
}