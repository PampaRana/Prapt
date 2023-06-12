package com.prapt.prapt.otherFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaopiz.kprogresshud.KProgressHUD
import com.prapt.prapt.R
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.adapter.MyCartAdapter
import com.prapt.prapt.apiCall.ApiClient
import com.prapt.prapt.apiCall.ApiNewClient
import com.prapt.prapt.databinding.MyCartBinding
import com.prapt.prapt.model.cart.CartData
import com.prapt.prapt.model.cart.CartDataDetails
import com.prapt.prapt.pogo.MycartsetGet
import com.prapt.prapt.utils.App
import com.prapt.prapt.utils.Config
import com.prapt.prapt.utils.InternetCheck
import com.prapt.prapt.utils.SharedPreferencesClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MycartFrag : NetworkObserverFragment(),
    View.OnClickListener {
    private lateinit var mBinding: MyCartBinding
    var recyclerView: RecyclerView? = null
    var MyCartAdapter: MyCartAdapter? = null

    //var MycartsetGet: List<MycartsetGet>? = null
    var cartList = ArrayList<CartDataDetails>()

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
        mBinding = MyCartBinding.inflate(inflater, container, false)
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
        /*if (InternetCheck.isConnected(context)) {
            showHud()
            getCartPdtLIst()
        } else {
            showToastMessage("No internet connection")
        }*/

        //MycartsetGet = ArrayList()
        /*(MycartsetGet as java.util.ArrayList<MycartsetGet>).add(
            MycartsetGet(
                "1",
                "Sunlight Eb Powder",
                "500g",
                "₹59.63",
                "₹65",
                "1",
                R.drawable.sunlight
            )
        )

        (MycartsetGet as java.util.ArrayList<MycartsetGet>).add(
            MycartsetGet(
                "1",
                "Sunlight Eb Powder",
                "500g",
                "₹59.63",
                "₹65",
                "2",
                R.drawable.sunlight
            )
        )

        (MycartsetGet as java.util.ArrayList<MycartsetGet>).add(
            MycartsetGet(
                "1",
                "Sunlight Eb Powder",
                "500g",
                "₹59.63",
                "₹65",
                "3",
                R.drawable.sunlight
            )
        )*/

    }
    var hud: KProgressHUD? = null

    fun showHud() {
        hud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show()
    }

    fun hide() {
        hud!!.dismiss()
    }
    private fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    private fun getCartPdtLIst() {
        showHud()
        val call = ApiNewClient.getInstance(context).getCartList(
            SharedPreferencesClass.retrieveData(context, Config.USER_ID)
        )
        call.enqueue(object : Callback<CartData?> {
            override fun onResponse(call: Call<CartData?>, response: Response<CartData?>) {
                hide()
                if (response.isSuccessful) {
                    cartList.clear()
                    assert(response.body() != null)
                    if (response.body()!!.success == "true") {
                        for (i in response.body()!!.cartDetailsDataList.indices) {
                            cartList.add(response.body()!!.cartDetailsDataList[i])
                        }
                        recyclerView!!.setHasFixedSize(true)
//        val layoutManager: RecyclerView.LayoutManager =
//            GridLayoutManager(context, 1)
                        recyclerView?.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

//        recyclerView!!.layoutManager = layoutManager
                        recyclerView!!.adapter = MyCartAdapter
                        val adapter =
                            MyCartAdapter(context, cartList)
                        recyclerView?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<CartData?>, t: Throwable) {
                hide()
            }
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

}