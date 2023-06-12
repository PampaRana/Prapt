package com.prapt.prapt.otherFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prapt.prapt.R
import com.prapt.prapt.pogo.WishListSetGet
import com.prapt.prapt.adapter.WishlistViewAdapter
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.databinding.MyWishlistBinding
import com.prapt.prapt.utils.App

class Mywishlist  : NetworkObserverFragment(),
    View.OnClickListener {
    private lateinit var mBinding: MyWishlistBinding
    var recyclerView: RecyclerView? = null
    var WishlistViewAdapter: WishlistViewAdapter? = null
    var wishListSetGet: List<WishListSetGet>? = null
    private var mScrollY = 0
    private var mCurrentTime = System.currentTimeMillis()
    var lv1: LinearLayout? = null
    private var lock = true
    companion object {
        private const val OFFSET = 0
        private const val LIMIT = 10
        private const val TAG = "HomeFrag"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MyWishlistBinding.inflate(inflater, container, false)
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
        recyclerView = view?.findViewById<View>(R.id.recyclerView) as RecyclerView
        wishListSetGet = ArrayList()
        (wishListSetGet as java.util.ArrayList<WishListSetGet>).add(
            WishListSetGet(
                "1",  //"Air Booking",
                "Sunlight Eb Power",
                "500g",
                "₹59.63",
                "₹65",
                R.drawable.sunlight
            )
        )
        (wishListSetGet as java.util.ArrayList<WishListSetGet>).add(
            WishListSetGet(
                "1",  //"Air Booking",
                "Sunlight Eb Power",
                "500g",
                "₹59.63",
                "₹65",
                R.drawable.sunlight
            )
        )
        (wishListSetGet as java.util.ArrayList<WishListSetGet>).add(
            WishListSetGet(
                "1",  //"Air Booking",
                "Sunlight Eb Power",
                "500g",
                "₹59.63",
                "₹65",
                R.drawable.sunlight
            )
        )
        (wishListSetGet as java.util.ArrayList<WishListSetGet>).add(
            WishListSetGet(
                "1",  //"Air Booking",
                "Sunlight Eb Power",
                "500g",
                "₹59.63",
                "₹65",
                R.drawable.sunlight
            )
        )
        (wishListSetGet as java.util.ArrayList<WishListSetGet>).add(
            WishListSetGet(
                "1",  //"Air Booking",
                "Sunlight Eb Power",
                "500g",
                "₹59.63",
                "₹65",
                R.drawable.sunlight
            )
        )
        (wishListSetGet as java.util.ArrayList<WishListSetGet>).add(
            WishListSetGet(
                "1",  //"Air Booking",
                "Sunlight Eb Power",
                "500g",
                "₹59.63",
                "₹65",
                R.drawable.sunlight
            )
        )
        recyclerView!!.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = WishlistViewAdapter
        val adapter =
            WishlistViewAdapter(context, wishListSetGet)
        recyclerView?.adapter = adapter
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

}