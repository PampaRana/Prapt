package com.prapt.prapt.otherFrag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TabHost
import android.widget.TabHost.OnTabChangeListener
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.prapt.prapt.R
import com.prapt.prapt.adapter.TabsAdapter
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.databinding.OfferZoneBinding
import com.prapt.prapt.utils.App

class OfferZoneFrag : NetworkObserverFragment(),
    View.OnClickListener, OnTabChangeListener {
    private lateinit var mBinding: OfferZoneBinding
    var tabLayout: TabLayout? = null
    var tabHost: TabHost? = null
    var viewPager: ViewPager? = null
    private val tabicons = intArrayOf(
        R.drawable.persent1,
        R.drawable.persent1
    )
    private var mScrollY = 0
    private var mCurrentTime = System.currentTimeMillis()
    var lv1: LinearLayout? = null
    private var lock = true
    companion object {
        private const val OFFSET = 0
        private const val LIMIT = 10
        private const val TAG = "OfferZoneFrag"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = OfferZoneBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }
    private fun init() {
        (activity as MainActivity).hideToolbar(
            App.getShapeFromColor(
                mBinding.homeHeaderContainer,
                R.color.blue_naviBar,
                20f
            )
        )
        (activity as MainActivity).setContainer(false)
        (activity as MainActivity).showHome()
        (activity as MainActivity).uncheckAllItems()
        (activity as MainActivity).setDrawerLock(false)
//      App.initHeader(mBinding.homeStatusBar)
        mBinding.homeHeaderNavBtn.setOnClickListener(this)
        tabLayout = view?.findViewById<View>(R.id.tab_layout) as TabLayout
        viewPager = view?.findViewById<View>(R.id.view_pager) as ViewPager
        tabLayout!!.addTab(tabLayout!!.newTab().setText("All Offers"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Top Offers"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        val tabsAdapter = TabsAdapter(
            requireActivity().supportFragmentManager,
            tabLayout!!.tabCount
        )
        viewPager!!.adapter = tabsAdapter

        // tabLayout = getHost();
        // tabLayout.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.ab);
        // tabLayout.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.ab);
        // tabLayout.getTabWidget().setCurrentTab(0);
        // tabLayout.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.abch);
        tabLayout!!.getTabAt(0)!!.setIcon(tabicons[0])
        tabLayout!!.getTabAt(1)!!.setIcon(tabicons[1])
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
                if (tab.position === 0) {
                    Log.e("TABB", "0")
                } else if (tab.position === 1) {
                    Log.e("TABB", "1")
                } else {
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.homeHeaderNavBtn -> {
                (activity as MainActivity).openDrawer()
            }

        }
    }

    override fun onTabChanged(tabId: String?) {
        TODO("Not yet implemented")
    }

}