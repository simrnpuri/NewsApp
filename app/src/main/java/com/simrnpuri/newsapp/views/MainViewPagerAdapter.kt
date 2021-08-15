package com.simrnpuri.newsapp.views
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simrnpuri.newsapp.data.MainTab


class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = MainTab.values().size

    override fun createFragment(position: Int): Fragment {
        return NewsListFragment().apply {
            arguments = bundleOf("main_tab" to MainTab.values()[position])
        }
    }
}

