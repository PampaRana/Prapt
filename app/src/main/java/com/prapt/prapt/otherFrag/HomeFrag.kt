package com.prapt.prapt.otherFrag
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.kaopiz.kprogresshud.KProgressHUD
import com.prapt.prapt.R
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.adapter.AdapterCategories
import com.prapt.prapt.adapter.SliderAdapter
import com.prapt.prapt.adapter.TopOfferAdapter
import com.prapt.prapt.adapter.TopPacksAdapter
import com.prapt.prapt.apiCall.ApiNewClient
import com.prapt.prapt.databinding.FragHomeBinding
import com.prapt.prapt.model.banner.BannerData
import com.prapt.prapt.model.banner.BannerDataDetails
import com.prapt.prapt.model.topOffer.TopOfferData
import com.prapt.prapt.model.topOffer.TopOfferDataDetails
import com.prapt.prapt.pogo.Categories
import com.prapt.prapt.pogo.Toppacks
import com.prapt.prapt.utils.App
import com.prapt.prapt.utils.Config
import com.prapt.prapt.utils.InternetCheck
import com.prapt.prapt.utils.SharedPreferencesClass
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFrag : NetworkObserverFragment(),
    View.OnClickListener {
    private lateinit var mBinding: FragHomeBinding
    private var mScrollY = 0
    private var mCurrentTime = System.currentTimeMillis()
    var lv1: LinearLayout? = null
    private var lock = true
    companion object {
        private const val OFFSET = 0
        private const val LIMIT = 10
        private const val TAG = "HomeFrag"
    }
    private val mPager: ViewPager? = null
    private val currentPage = 0
    private val NUM_PAGES = 0
    var bannerList = ArrayList<BannerDataDetails>()

    private val IMAGES = arrayOf<Int>(
        R.mipmap.top_banner, R.mipmap.top_banner2,
        R.mipmap.top_banner3
    )
    private val ImagesArray = ArrayList<Int>()
    var sliderView: SliderView? = null

    var recyclerview: RecyclerView? = null
    var recyclerview1: RecyclerView? = null
    var recyclerview2: RecyclerView? = null
    var recyclerview3: RecyclerView? = null
    var categories: List<Categories>? = null
    var homeItemViewAdapter: AdapterCategories? = null
    //var toppacks: List<Toppacks>? = null
    private val sliderHandler: Handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragHomeBinding.inflate(inflater, container, false)
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
        recyclerview = view?.findViewById(R.id.recyclerview)
        recyclerview1 = view?.findViewById(R.id.recyclerview1)
        recyclerview2 = view?.findViewById(R.id.recyclerview2)
        recyclerview3 = view?.findViewById(R.id.recyclerview3)
        sliderView = view?.findViewById(R.id.slider)

        if (InternetCheck.isConnected(context)) {
            showHud()
            getBannerList()
        } else {
            showToastMessage("No internet connection")
        }

        categories = java.util.ArrayList()
        /////////////////////////////////////////////////////////////////////////////////
        (categories as java.util.ArrayList<Categories>).add(
            Categories(
                "1",  //"Air Booking",
                "Categories",
                R.mipmap.categories
            )
        )
        (categories as java.util.ArrayList<Categories>).add(
            Categories(
                "2",  //"Air Booking",
                "Biscuits",
                R.mipmap.biscuits
            )
        )
        (categories as java.util.ArrayList<Categories>).add(
            Categories(
                "3",  //"Air Booking",
                "Detergents",
                R.mipmap.detergents
            )
        )
        (categories as java.util.ArrayList<Categories>).add(
            Categories(
                "4",  //"Air Booking",
                "Oils",
                R.mipmap.oils
            )
        )
        (categories as java.util.ArrayList<Categories>).add(
            Categories(
                "5",
                "Rice",
                R.mipmap.rice
            )
        )
        recyclerview?.setHasFixedSize(true)
        ////////////////////////////////////////////////
//        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 4)
//        recyclerview?.setLayoutManager(layoutManager)
//        val manager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
//        recyclerview?.setLayoutManager(manager)
//        homeItemViewAdapter = AdapterCategories(context, categories)
//        recyclerview?.setAdapter(homeItemViewAdapter)
        recyclerview?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val adapter =
            AdapterCategories(context, categories)
        recyclerview?.adapter = adapter

        if (InternetCheck.isConnected(activity)) {

            getTopOfferList()
        } else {
            showToastMessage("No internet connection")
        }
       /* toppacks = java.util.ArrayList()
        (toppacks as java.util.ArrayList<Toppacks>).add(
            Toppacks(
                "1",  //"Air Booking",
                R.mipmap.image1
            )
        )

        (toppacks as java.util.ArrayList<Toppacks>).add(
            Toppacks(
                "2",  //"Air Booking",
                R.mipmap.image2
            )
        )
        (toppacks as java.util.ArrayList<Toppacks>).add(
            Toppacks(
                "3",  //"Air Booking",
                R.mipmap.image3
            )
        )*/
        recyclerview1?.setHasFixedSize(true)
        recyclerview1?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        recyclerview2?.setHasFixedSize(true)
        recyclerview2?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        recyclerview3?.setHasFixedSize(true)
        recyclerview3?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)


    }

    private fun getBannerList() {

        val call = ApiNewClient.getInstance(context).getBannerList(
            SharedPreferencesClass.retrieveData(context, Config.USER_ID)
        )
        call.enqueue(object : Callback<BannerData?> {
            override fun onResponse(call: Call<BannerData?>, response: Response<BannerData?>) {
                hide()
                if (response.isSuccessful) {

                    bannerList.clear()
                    assert(response.body() != null)
                    if (response.body()!!.success == "true") {
                        for (i in response.body()!!.bannerDataDetails.indices) {
                            bannerList.add(response.body()!!.bannerDataDetails[i])
                        }

                        // passing this array list inside our adapter class.
                        val adapter = SliderAdapter(context, bannerList)

                        // below method is used to set auto cycle direction in left to
                        // right direction you can change according to requirement.
                        sliderView!!.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

                        // below method is used to
                        // setadapter to sliderview.

                        // below method is used to
                        // setadapter to sliderview.
                        sliderView!!.setSliderAdapter(adapter)

                        // below method is use to set
                        // scroll time in seconds.

                        // below method is use to set
                        // scroll time in seconds.
                        sliderView!!.scrollTimeInSec = 3

                        // to set it scrollable automatically
                        // we use below method.

                        // to set it scrollable automatically
                        // we use below method.
                        sliderView!!.isAutoCycle = true

                        // to start autocycle below method is used.

                        // to start autocycle below method is used.
                        sliderView!!.startAutoCycle()
                    }

                }
            }

            override fun onFailure(call: Call<BannerData?>, t: Throwable) {
                hide()
            }
        })
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
    private fun getTopOfferList() {
        // showHud();
        val topOfferList = java.util.ArrayList<TopOfferDataDetails>()
        val call = ApiNewClient.getInstance(context).getTopOfferList(
            SharedPreferencesClass.retrieveData(context, Config.USER_ID)
        )
        call.enqueue(object : Callback<TopOfferData?> {
            override fun onResponse(call: Call<TopOfferData?>, response: Response<TopOfferData?>) {
                hide()
                if (response.isSuccessful) {
                    assert(response.body() != null)
                    if (response.body()!!.success == "true") {
                        topOfferList.clear()
                        for (i in response.body()!!.topOfferDataDetails.indices) {
                            topOfferList.add(response.body()!!.topOfferDataDetails[i])
                        }
                       // val adapter = TopOfferAdapter(context, topOfferList)
                        val adapter1 = TopPacksAdapter(context, topOfferList)
                        recyclerview1?.adapter = adapter1

                        val adapter2 = TopPacksAdapter(context, topOfferList)
                        recyclerview2?.adapter = adapter2

                        val adapter3 = TopPacksAdapter(context, topOfferList)
                        recyclerview3?.adapter = adapter3
                    } else {
                        //showToastMessage(response.body().getMessage());
                    }
                }
            }

            override fun onFailure(call: Call<TopOfferData?>, t: Throwable) {}
        })
    }
}