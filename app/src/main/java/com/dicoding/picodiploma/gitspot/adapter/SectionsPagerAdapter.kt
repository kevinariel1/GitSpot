package com.dicoding.picodiploma.gitspot.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.picodiploma.gitspot.ui.FollowFragment

class SectionsPagerAdapter(private val context: Context, private val username: String) : FragmentStateAdapter(context as FragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle().apply {
                    putInt(FollowFragment.ARG_POSITION, FollowFragment.POSITION_FOLLOWER)
                    putString(FollowFragment.ARG_USERNAME, username)
                }
                FollowFragment().apply {
                    arguments = bundle
                }
            }
            else -> {
                val bundle = Bundle().apply {
                    putInt(FollowFragment.ARG_POSITION, FollowFragment.POSITION_FOLLOWING)
                    putString(FollowFragment.ARG_USERNAME, username)
                }
                FollowFragment().apply {
                    arguments = bundle
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}