package com.prapt.prapt.otherFrag
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prapt.prapt.R
import com.prapt.prapt.activity.DamageActivity
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.adapter.DamageAdapter
import com.prapt.prapt.databinding.DamageFragementBinding
import com.prapt.prapt.pogo.DamageSetGet
import com.prapt.prapt.utils.App

class DamageFrag :  NetworkObserverFragment(),
View.OnClickListener {
    private lateinit var mBinding: DamageFragementBinding
    var recyclerView: RecyclerView? = null
    var newRequestId: TextView?= null
    var WishlistViewAdapter: DamageAdapter? = null
    var damageSetGet: List<DamageSetGet>? = null
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
        mBinding = DamageFragementBinding.inflate(inflater, container, false)
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
        newRequestId = view?.findViewById<View>(R.id.newRequestId) as TextView
        newRequestId!!.setOnClickListener {
            System.out.println("Damage"+"0");
            val intent = Intent(context, DamageActivity::class.java)
            startActivity(intent)
        }
        recyclerView = view?.findViewById<View>(R.id.recyclerView) as RecyclerView
        damageSetGet = ArrayList()
        (damageSetGet as java.util.ArrayList<DamageSetGet>).add(
            DamageSetGet(
                "1",  //"Air Booking",
                "Subhasish Singha",
                "Item:2",
                "Request Amount:664",
                "1 Feb",
                "In Progress"
            )
        )
        (damageSetGet as java.util.ArrayList<DamageSetGet>).add(
            DamageSetGet(
                "1",  //"Air Booking",
                "Kunal",
                "Item:2",
                "Request Amount:664",
                "1 Feb",
                "In Progress"
            )
        )
        (damageSetGet as java.util.ArrayList<DamageSetGet>).add(
            DamageSetGet(
                "1",  //"Air Booking",
                "sudip Saha",
                "Item:2",
                "Request Amount:664",
                "1 Feb",
                "Complited"
            )
        )
        recyclerView!!.setHasFixedSize(true)
//        val layoutManager: RecyclerView.LayoutManager =
//            GridLayoutManager(context, 1)
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

//        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = WishlistViewAdapter
        val adapter =
            DamageAdapter(context, damageSetGet)
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