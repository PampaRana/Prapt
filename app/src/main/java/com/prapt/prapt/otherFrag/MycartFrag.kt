package com.prapt.prapt.otherFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaopiz.kprogresshud.KProgressHUD
import com.prapt.prapt.R
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.adapter.MyCartAdapter
import com.prapt.prapt.apiCall.ApiNewClient
import com.prapt.prapt.databinding.MyCartBinding
import com.prapt.prapt.model.SuccessData
import com.prapt.prapt.model.cart.CartData
import com.prapt.prapt.model.cart.CartDataDetails
import com.prapt.prapt.utils.App
import com.prapt.prapt.utils.Config
import com.prapt.prapt.utils.InternetCheck
import com.prapt.prapt.utils.SharedPreferencesClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode
import java.text.DecimalFormat

class MycartFrag : NetworkObserverFragment(),
    View.OnClickListener,MyCartAdapter.addToCartListener{
    private lateinit var mBinding: MyCartBinding
    var recyclerView: RecyclerView? = null
    var MyCartAdapter: MyCartAdapter? = null
    var cartPrice:TextView?=null
    var discountText:TextView?=null

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
        cartPrice = view?.findViewById<View>(R.id.cartPrice) as TextView
        discountText = view?.findViewById<View>(R.id.discountText) as TextView
        if (InternetCheck.isConnected(context)) {
            showHud()
            getCartPdtLIst()

        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }
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
                            MyCartAdapter(
                                context, cartList, cartPrice, discountText,
                            ) { product_id: String, quantity: String? ->
                                addToCartItem(
                                    product_id,
                                    quantity
                                )
                            }
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

    override fun addToCartItem(product_id: String?, quantity: String?) {
        showHud()
        val call = ApiNewClient.getInstance(context).getAddToCart(
            SharedPreferencesClass.retrieveData(context, Config.USER_ID),
            product_id, quantity
        )
        call.enqueue(object : Callback<SuccessData> {
            override fun onResponse(call: Call<SuccessData>, response: Response<SuccessData>) {
                if (response.isSuccessful) {
                    if (response.body()!!.success == "true") {
                        hide()
                        Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                        var totalActualPrice = 0.0
                        var totalDiscountPrice = 0.0
                        for (i in cartList.indices) {
                            totalActualPrice += cartList.get(i).getActual_price()
                                .toDouble() * cartList.get(i).getQuantity().toDouble()
                            totalDiscountPrice += cartList.get(i).getAfter_discount_price()
                                .toDouble() * cartList.get(i).getQuantity().toDouble()
                        }
                        val df = DecimalFormat("#.##")
                        df.roundingMode = RoundingMode.DOWN
                        val roundoffActualPrice = df.format(totalActualPrice)
                        val roundoffDiscountPrice = df.format(totalDiscountPrice)

                        cartPrice!!.text = "₹$roundoffActualPrice"
                        discountText!!.text = "₹$roundoffDiscountPrice"
                    //getCartPdtLIst()
                    } else {
                    }
                }
            }

            override fun onFailure(call: Call<SuccessData>, t: Throwable) {}
        })

    }

}