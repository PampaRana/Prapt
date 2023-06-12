package com.prapt.prapt.otherFrag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prapt.prapt.R
import com.prapt.prapt.activity.MainActivity
import com.prapt.prapt.adapter.LanguageAdapter
import com.prapt.prapt.databinding.ChooseLanguageBinding
import com.prapt.prapt.pogo.Languagesetget
import com.prapt.prapt.utils.App

class ChooseLanguage : NetworkObserverFragment(),
    View.OnClickListener {
    private lateinit var mBinding: ChooseLanguageBinding
    var recyclerView: RecyclerView? = null
    var languageAdapter: LanguageAdapter? = null
    var languagesetget: List<Languagesetget>? = null
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
        mBinding = ChooseLanguageBinding.inflate(inflater, container, false)
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
        languagesetget = ArrayList()
        (languagesetget as java.util.ArrayList<Languagesetget>).add(
            Languagesetget(
                "1",
                "हिंदी",
                "Hindi",
                R.drawable.hindic
            )
        )

//        (languagesetget as java.util.ArrayList<Languagesetget>).add(
//            Languagesetget(
//                "1",
//                "తెలుగు",
//                "telugu",
//                R.drawable.taj
//            )
//        )
//
//        (languagesetget as java.util.ArrayList<Languagesetget>).add(
//            Languagesetget(
//                "1",
//                "தமிழ்",
//                "Tamil",
//                R.drawable.taj
//            )
//        )

        (languagesetget as java.util.ArrayList<Languagesetget>).add(
            Languagesetget(
                "1",
                "English",
                "English",
                R.drawable.englishc
            )
        )
        (languagesetget as java.util.ArrayList<Languagesetget>).add(
            Languagesetget(
                "1",
                "বাংলা",
                "Bengali",
                R.drawable.bengalic
            )
        )
//
//        (languagesetget as java.util.ArrayList<Languagesetget>).add(
//            Languagesetget(
//                "1",
//                "मराठी",
//                "Marathi",
//                R.drawable.taj
//            )
//        )
//        (languagesetget as java.util.ArrayList<Languagesetget>).add(
//            Languagesetget(
//                "1",
//                "मराठी",
//                "Marathi",
//                R.drawable.taj
//            )
//        )
//        (languagesetget as java.util.ArrayList<Languagesetget>).add(
//            Languagesetget(
//                "1",
//                "ಕನ್ನಡ",
//                "Kannada",
//                R.drawable.taj
//            )
//        )
//        (languagesetget as java.util.ArrayList<Languagesetget>).add(
//            Languagesetget(
//                "1",
//                "ଓଡିଆ",
//                "Odia",
//                R.drawable.taj
//            )
//        )
        recyclerView!!.setHasFixedSize(true)
//        val layoutManager: RecyclerView.LayoutManager =
//            GridLayoutManager(context, 1)
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = languageAdapter
        val adapter =
            LanguageAdapter(context, languagesetget)
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