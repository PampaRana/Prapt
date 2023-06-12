package com.prapt.prapt.otherFrag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.StringRequest
import com.kaopiz.kprogresshud.KProgressHUD
import com.prapt.prapt.R
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.adapter.CategoryAdapter
import com.prapt.prapt.apiCall.ApiNewClient
import com.prapt.prapt.apiCall.VolleySingleton
import com.prapt.prapt.databinding.CategoriesFragmentBinding
import com.prapt.prapt.model.category.CategoryData
import com.prapt.prapt.model.category.CategoryDataDetails
import com.prapt.prapt.pogo.LoginModel
import com.prapt.prapt.utils.*
import com.prapt.prapt.utils.Config.BASEURL
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatagoriFrag  : NetworkObserverFragment(),
    View.OnClickListener {
    private lateinit var mBinding: CategoriesFragmentBinding
    var recyclerView: RecyclerView? = null
    var itemViewAdapter: CategoryAdapter? = null
    //var itemViews: List<ItemView>? = null
    var catList: ArrayList<CategoryDataDetails?>? = ArrayList()

    private var mScrollY = 0
    private var mCurrentTime = System.currentTimeMillis()
    var lv1: LinearLayout? = null
    private var lock = true
    companion object {
        private const val OFFSET = 0
        private const val LIMIT = 10
        private const val TAG = "CateFrag"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = CategoriesFragmentBinding.inflate(inflater, container, false)
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

        //itemViews = ArrayList()

        if (InternetCheck.isConnected(context)) {
            showHud()
            getCategoryList(SharedPreferencesClass.retrieveData(context,Config.USER_ID))
        } else {
            showToastMessage("No internet connection")
        }
        /////////////////////////////////////////////////////////////////////////////////
        /*(itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "1",  //"Air Booking",
                "Categories",
                R.mipmap.categories
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "2",  //"Air Booking",
                "Biscuits",
                R.mipmap.biscuits
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "3",  //"Air Booking",
                "Detergents",
                R.mipmap.detergents
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "4",  //"Air Booking",
                "Oils",
                R.mipmap.oils
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "5",
                "Rice",
                R.mipmap.rice
            )
        )

        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "1",  //"Air Booking",
                "Categories",
                R.mipmap.categories
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "2",  //"Air Booking",
                "Biscuits",
                R.mipmap.biscuits
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "3",  //"Air Booking",
                "Detergents",
                R.mipmap.detergents
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "4",  //"Air Booking",
                "Oils",
                R.mipmap.oils
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "5",
                "Rice",
                R.mipmap.rice
            )
        )

        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "1",  //"Air Booking",
                "Categories",
                R.mipmap.categories
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "2",  //"Air Booking",
                "Biscuits",
                R.mipmap.biscuits
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "3",  //"Air Booking",
                "Detergents",
                R.mipmap.detergents
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "4",  //"Air Booking",
                "Oils",
                R.mipmap.oils
            )
        )

        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "1",  //"Air Booking",
                "Categories",
                R.mipmap.categories
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "2",  //"Air Booking",
                "Biscuits",
                R.mipmap.biscuits
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "3",  //"Air Booking",
                "Detergents",
                R.mipmap.detergents
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "5",
                "Rice",
                R.mipmap.rice
            )
        )

        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "1",  //"Air Booking",
                "Categories",
                R.mipmap.categories
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "2",  //"Air Booking",
                "Biscuits",
                R.mipmap.biscuits
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "3",  //"Air Booking",
                "Detergents",
                R.mipmap.detergents
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "4",  //"Air Booking",
                "Oils",
                R.mipmap.oils
            )
        )
        (itemViews as java.util.ArrayList<ItemView>).add(
            ItemView(
                "5",
                "Rice",
                R.mipmap.rice
            )
        )*/

        //categories()
    }

    private fun getCategoryList(user_id: String?) {
        val call = ApiNewClient.getInstance(context).getCategoryList(user_id)
        call.enqueue(object : Callback<CategoryData?> {
            override fun onResponse(call: Call<CategoryData?>, response: Response<CategoryData?>) {
                hide()
                if (response.isSuccessful) {
                    assert(response.body() != null)
                    catList!!.clear()
                    if (response.body()!!.success == "true") {

                        for (i in response.body()!!.categoryDetailsDataList.indices) {
                            catList!!.add(response.body()!!.categoryDetailsDataList[i])
                        }

                        recyclerView!!.setHasFixedSize(true)
                        val layoutManager: RecyclerView.LayoutManager =
                            GridLayoutManager(context, 4)
                        recyclerView!!.layoutManager = layoutManager
                        recyclerView!!.adapter = itemViewAdapter
                        val adapter = CategoryAdapter(
                            context,
                            catList
                        )
                        recyclerView?.adapter = adapter

                        //showToastMessage(response.body()!!.message)
                    } else {
                        showToastMessage(response.body()!!.message)
                    }
                }
            }

            override fun onFailure(call: Call<CategoryData?>, t: Throwable) {

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

    private fun categories(){
        val user: LoginModel = SharedPrefManagerLogin.getInstance(context).user

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, BASEURL+"get_categories",
            com.android.volley.Response.Listener { response ->
                Log.e("get_categories", response!!)
                try {
                    val obj = JSONObject(response)
                    val success = obj.getString("success")
                    val Message = obj.getString("Message")
                    if(success.equals("true")) {

                    }else{

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            com.android.volley.Response.ErrorListener {
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["id"] = user.userId
                params["token"] = user.token
                System.out.println("reject_leads_list" + user.token)
                return params
            }

//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = java.util.HashMap<String, String>()
////              headers.put("Content-Type", "application/json");
//                //headers.put("Content-Type", "application/json");
//                headers["Authorization"] = "Bearer " + user.token
//                System.out.println("reject_leads_list" + user.token)
//                return super.getHeaders()
//            }
        }
        stringRequest.retryPolicy =
            DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        val volleySingleton = VolleySingleton.getInstance(context)
        stringRequest.setShouldCache(false)
        volleySingleton.addToRequestQueue(stringRequest)
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
    private fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

