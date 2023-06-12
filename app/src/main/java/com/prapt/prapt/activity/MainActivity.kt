package com.prapt.prapt.activity
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.textview.MaterialTextView
import com.prapt.prapt.*
import com.prapt.prapt.adapter.SlideMenuRvAdapter
import com.prapt.prapt.utils.CustomDuoDrawerLayout.LOCK_MODE_LOCKED_CLOSED
import com.prapt.prapt.utils.CustomDuoDrawerLayout.LOCK_MODE_UNLOCKED
import com.prapt.prapt.databinding.ActivityMainBinding
import com.prapt.prapt.interfacelistener.ItemCallback
import com.prapt.prapt.interfacelistener.OnItemClickListener
import com.prapt.prapt.otherFrag.*
import com.prapt.prapt.utils.*
import com.robinhood.ticker.TickerUtils
class MainActivity : AppCompatActivity(), View.OnClickListener,
    OnItemClickListener,
    NavigationBarView.OnItemSelectedListener, DrawerLayout.DrawerListener{
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mRequestToLoginContract: ActivityResultLauncher<Intent>
    private lateinit var mPaymentStatusContract: ActivityResultLauncher<Intent>
    private var mCurrentBackStackCount = 1
    private var mStatusBarColor = 0
    private var mClickCallback: ItemCallback<Any>? = null
    private var mRefreshListener: OnRefreshListener? = null
    private var mTopShape: ViewShape? = null
    private var mBottomShape: ViewShape? = null
    private var mCurrentFrag: Fragment? = null
    private var mDrawerSlideListener: DrawerSlideListener? = null
    private var help_center: MaterialTextView? = null
    private var cus_service: MaterialTextView? = null
    private var MaterialTextView:MaterialTextView? = null
    var id:String = "00"
    interface OnRefreshListener {
        fun refresh()
    }

    interface DrawerSlideListener {
        fun onDrawerSlide(corner: Float)
    }

    companion object {
        private const val MANUAL_OPEN = -1
    }

    fun setOnDrawerSlideListener(listener: DrawerSlideListener?) {
        mDrawerSlideListener = listener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
    }
    private fun init() {
        val intent = intent
        id = intent.getStringExtra("id").toString()
        initNavDrawer()
        initContracts()
        initToolbar()
        if (id.equals("0")){
            initHome()
        }
        else if (id.equals("1")){
            transact(CatagoriFrag(), false, false)
        }
        else{
            initHome()
        }
        initClickListeners()
        initNeedToRegister()
        mCurrentBackStackCount = supportFragmentManager.backStackEntryCount
        help_center = findViewById<View>(R.id.help_center) as MaterialTextView
        cus_service = findViewById<View>(R.id.cus_service) as MaterialTextView
        help_center!!.setOnClickListener {
            val intent = Intent(applicationContext, HelpCenterActivity::class.java)
            startActivity(intent)
        }
        cus_service!!.setOnClickListener {
            val intent = Intent(applicationContext, CustomerSeerviceActivity::class.java)
            startActivity(intent)
        }
        val slideLogInOutBtn = findViewById<MaterialTextView>(R.id.slideLogInOutBtn) as MaterialTextView
        slideLogInOutBtn!!.setOnClickListener {
            SharedPrefManagerLogin.getInstance(applicationContext).logout()
            SharedPrefManagerLoginMach.getInstance(applicationContext).logout()
            val intent = Intent(applicationContext, SecondScreenActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initToolbar() {
        mBinding.mainToolbarEndCircleView.setCharacterLists(TickerUtils.provideNumberList())
        mBinding.mainToolbarEndCircleView.typeface =
            ResourcesCompat.getFont(this, R.font.regular)
    }
    private fun initHome() {
        transact(HomeFrag(), false, false)
    }
    private fun initContracts() {
        mRequestToLoginContract =
            registerForActivityResult(ResultContracts.ActivityResultResponse()) { intent ->
                if (intent != null && intent.getBooleanExtra(
                        App.REQUEDT_TO_LOGIN_FROM_INSIDE_APP,
                        false
                    )
                ) {
                } else {
                    refreshCurrentFrag()
                }

            }
        mPaymentStatusContract =
            registerForActivityResult(ResultContracts.ActivityResultResponse()) { intent ->
                if (intent != null) {

                }
            }
    }

    override fun onStart() {
        super.onStart()

    }
    fun goToLoginPage(listener: OnRefreshListener? = null) {
        mRefreshListener = listener

    }
    private fun initNavDrawer() {
        mBinding.drawerLayout.setDrawerListener(this)
        val items = ArrayList<MenuItem>()
        items.add(MenuItem(R.drawable.home_home, getString(R.string.home)))
        items.add(MenuItem(R.drawable.home_catagories,getString(R.string.categories)))
        items.add(MenuItem(R.drawable.offer_zone, getString(R.string.offer_zone)))
        items.add(MenuItem(R.drawable.home_language, getString(R.string.choose_language)))
        items.add(MenuItem(R.drawable.home_damage, getString(R.string.damage_expiry)))
        items.add(MenuItem(R.drawable.home_mycart, getString(R.string.my_cart)))
        items.add(MenuItem(R.drawable.home_wish, getString(R.string.my_wishlist)))
        items.add(MenuItem(R.drawable.home_notifications, getString(R.string.notifications)))
//        items.add(MenuItem(R.drawable.ic_home, getString(R.string.home)))
//        items.add(MenuItem(R.drawable.ic_home, getString(R.string.home)))
//        items.add(MenuItem(R.drawable.ic_home, getString(R.string.home)))
//        items.add(MenuItem(R.drawable.ic_home, getString(R.string.home)))
//        items.add(MenuItem(R.drawable.ic_home, getString(R.string.home)))

        val adapter = SlideMenuRvAdapter(items)
        mBinding.slideMenuRv.adapter = adapter
        mBinding.slideMenuRv.addOnItemTouchListener(
            ItemClickListener(
                mBinding.slideMenuRv,
                this
            )
        )
    }
    private fun initNeedToRegister() {
        if (intent != null) {

        }


    }
    private fun initClickListeners() {
        mBinding.mainHomeContainer.setOnClickListener(this)
        mBinding.bottomNav.setOnItemSelectedListener(this)
        mBinding.mainToolbarStartImg.setOnClickListener(this)
        mBinding.mainToolbarEndImg.setOnClickListener(this)
        mBinding.slideUserSettingsBtn.setOnClickListener(this)
//      mBinding.slideLngContainer.setOnClickListener(this)
//      mBinding.slideLogInOutBtn.setOnClickListener(this)
    }

    fun openDrawer() {
        if (!mBinding.drawerLayout.isDrawerOpen) {
            mBinding.mainContainer.post {
                mBinding.drawerLayout.openDrawer()
            }
        }
    }

    fun showHome() {
        mBinding.mainHomeTv.visibility = View.VISIBLE
    }

    fun uncheckAllItems() {
        val menu = mBinding.bottomNav.menu
        menu.setGroupCheckable(0, true, false)
        for (i in 0 until menu.size()) {
            menu.getItem(i).isChecked = false
        }
        menu.setGroupCheckable(0, true, true)
    }

    fun setOnFilterButtonClickListener(callback: ItemCallback<Any>) {
        mClickCallback = callback
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.mainHomeContainer -> {
//                if (!mBinding.mainHomeTv.isVisible) {
//                    mBinding.mainHomeTv.visibility = View.VISIBLE
//                    transact(HomeFrag(), false, false)
//                }
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.mainToolbarStartImg, R.id.mainToolbarEndImg -> {
                when (v.tag as ToolbarOptions.Icon) {
                    ToolbarOptions.Icon.BACK -> {
                        onBackPressed()
                    }

                    ToolbarOptions.Icon.NAV -> {
                        openDrawer()
                    }

                    ToolbarOptions.Icon.CART -> {
//                        transact(CartFrag())
                    }

                    ToolbarOptions.Icon.FILTER -> {
                        mClickCallback?.onItem(Any())
                    }
                }
            }

            R.id.slideUserSettingsBtn -> {
//                if (!App.isLoggedIn()) {
//                    goToLoginPage(null)
//                    return
//                }
                if (!mBinding.drawerLayout.isDrawerOpen) {
                    return
                }
                mBinding.drawerLayout.closeDrawer()
//                Handler(Looper.getMainLooper()).postDelayed({
//                  transact(SettingsFrag())
//                }, 300)
//                val intent = Intent(applicationContext, SettingsFrag::class.java)
//                startActivity(intent)
            }
//            R.id.slideLngContainer -> {
//                if (!mBinding.drawerLayout.isDrawerOpen) {
//                    return
//                }
//
//                val bundle = Bundle()
//                bundle.putSerializable(App.SELECTION_TYPE, SelectionDialog.Selection.Language)
//
//                val instance = SelectionDialog.getInstance<Language>()
//                instance.setOnItemSelected(this)
//                instance.arguments = bundle
//                instance.show(supportFragmentManager, null)
//            }

           // R.id.slideLogInOutBtn -> {
//                if (!mBinding.drawerLayout.isDrawerOpen) {
//                    return
//                }

//                val btnText = mBinding.slideLogInOutBtn.text.toString()
//                if (btnText == getString(R.string.login)) {
//                    goToLoginPage(null)
//                } else {
//                    showLogoutConfirmation()
//                }
           // }
        }
    }

    private fun showLogoutConfirmation() {
        val dialog = AppDialog.instance
        val bdl = Bundle()
        bdl.putString(App.TITLE, getString(R.string.logout))
        bdl.putString(App.TEXT, getString(R.string.logout_desc))
        dialog.setOnDialogBtnsClickedListener(
            AppDialog.DialogType.YES_CANCEL,
            object : AppDialog.OnDialogCreated {
                override fun onCancel() {
                    dialog.dismiss()
                }

                override fun onOk() {
                    mBinding.drawerLayout.closeDrawer()
                    Handler(Looper.getMainLooper()).postDelayed({
                        logout()
                    }, 300)
                }

            })
        dialog.arguments = bdl
        dialog.show(supportFragmentManager, null)
    }

    private fun logout() {
//        mPresenter.logout()
//        App.logout(this)
    }

    private fun onBottomBarItemSelected(item: android.view.MenuItem?, itemId: Int?): Boolean {
        val fragment: Fragment

        when (item?.itemId ?: itemId!!) {
            R.id.categories_page -> {
                fragment = HomeFrag()
            }
            R.id.providers_page -> {
                fragment = HomeFrag()
            }
            R.id.blog_page -> {
                fragment = HomeFrag()
            }
            R.id.my_classes_page -> {
                fragment = HomeFrag()
            }

            else -> return false
        }

        val addToBackstack =
            mBinding.mainHomeTv.isVisible && supportFragmentManager.backStackEntryCount == 0

        mBinding.mainHomeTv.visibility = View.INVISIBLE
        transact(fragment, false, addToBackstack)

        return true
    }
    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        return onBottomBarItemSelected(item, null)
    }

    fun setStatusBarColor(color: Int) {
        mStatusBarColor = ContextCompat.getColor(this, color)
        window.statusBarColor = mStatusBarColor
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        val startColor = mStatusBarColor
        val endColor = ContextCompat.getColor(this, R.color.dark_green)

        window.statusBarColor = ColorUtils.blendARGB(startColor, endColor, slideOffset)

        val corner = Utils.changeDpToPx(this, slideOffset * 20)
        mDrawerSlideListener?.onDrawerSlide(corner)

//        setTopCorner(corner)
//        setBottomCorner(corner)
    }

    override fun onDrawerStateChanged(newState: Int) {
    }

    private fun setTopCorner(corner: Float) {
        val topShape = GradientDrawable()
        topShape.shape = GradientDrawable.RECTANGLE
        topShape.cornerRadii = floatArrayOf(corner, corner, corner, corner, 0f, 0f, 0f, 0f)
        topShape.setColor(ContextCompat.getColor(this, R.color.pageBg))

        if (mBinding.mainToolbar.isVisible) {
            mBinding.mainToolbar.background = topShape
        } else {
            mBinding.mainContainer.background = topShape
            var currentCorners = 0f
            if (mTopShape != null) {
                currentCorners = mTopShape!!.currentCorners
            }

            mTopShape?.viewBg?.cornerRadii = floatArrayOf(
                corner,
                corner,
                corner,
                corner,
                currentCorners,
                currentCorners,
                currentCorners,
                currentCorners
            )
            mTopShape?.view?.background = mTopShape?.viewBg
        }
    }

    private fun setBottomCorner(corner: Float) {
        val bottomShape = GradientDrawable()
        bottomShape.shape = GradientDrawable.RECTANGLE
        bottomShape.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, corner, corner, corner, corner)
        bottomShape.orientation = GradientDrawable.Orientation.BR_TL

        val navStartColor = ContextCompat.getColor(this, R.color.blue_naviBar)
        val navEndColor = ContextCompat.getColor(this, R.color.blue_naviBar)
        bottomShape.colors = intArrayOf(navStartColor, navEndColor)

        if (mBinding.bottomNav.isGone) {
            mBinding.bottomNav.background = bottomShape
        } else {
            bottomShape.setColor(ContextCompat.getColor(this, R.color.pageBg))
            if (mBottomShape == null) {
                mBinding.mainContainer.background = bottomShape
            } else {
                mBinding.mainContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.transparent
                    )
                )
                val bg = mBottomShape?.viewBg as GradientDrawable
                bg.cornerRadii =
                    floatArrayOf(corner, corner, corner, corner, corner, corner, corner, corner)

                mBottomShape?.view?.background = bg
//                mCurrentFrag?.view?.background = bg
            }
        }
    }

    override fun onBackPressed() {
        mCurrentBackStackCount = supportFragmentManager.backStackEntryCount

    }

    override fun onClick(view: View?, position: Int, id: Int) {
        var fragment: Fragment
        if (mBinding.drawerLayout.isDrawerOpen) {
            mBinding.drawerLayout.closeDrawer()
        } else if (id != MANUAL_OPEN) {
            return
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if (position == SlideMenuItem.NOTIFICATION.value() && mBinding.bottomNav.isGone) {
                mBinding.bottomNav.findViewById<View>(R.id.my_classes_page).performClick()
                mBinding.mainHomeTv.visibility = View.INVISIBLE
                return@postDelayed
            }
            when (position) {
                SlideMenuItem.HOME.value() -> {
                    if (!mBinding.mainHomeTv.isVisible) {
                        if (mBinding.bottomNav.isGone) {
                            super.onBackPressed()
                            System.out.println("Print_Data"+"00000");

                        } else {
//                            transact(HomeFrag(), false)
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            System.out.println("Print_Data"+"0000");

                        }
                    }
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    System.out.println("Print_Data"+"0");

                }
                SlideMenuItem.CATEGORIES.value() -> {
//                    val intent = Intent(applicationContext, CategoriesActivity::class.java)
//                    startActivity(intent)
                      transact(CatagoriFrag())
                    System.out.println("Print_Data"+"1");

                }
                SlideMenuItem.OFFERZONE.value() -> {
//                    transact(Mywishlist())
                    System.out.println("Print_Data"+"2");
                    transact(OfferZoneFrag())
                }

                SlideMenuItem.CHOOSELANGUAGE.value() -> {
//                    transact(MeetingsTabFrag())
                    System.out.println("Print_Data"+"3");
                    transact(ChooseLanguage())
                }

                SlideMenuItem.DAMAGEEXPRIY.value() -> {
//                    transact(AssignmentsTabFrag())
                    System.out.println("Print_Data"+"4");
                    transact(DamageFrag())
                }

                SlideMenuItem.MYCART.value() -> {
//                  transact(QuizzesTabFrag())
                    System.out.println("Print_Data"+"6");
                    transact(MycartFrag())
                }

                SlideMenuItem.MYWISHLIST.value() -> {
//                  transact(CertificatesTabFrag())
                    System.out.println("Print_Data"+"7");
                    transact(Mywishlist())
                }

                SlideMenuItem.NOTIFICATION.value() -> {
//                    transact(FavoritesFrag())
                    System.out.println("Print_Data"+"7");
                }

            }
        }, 300)
    }

    override fun onLongClick(view: View?, position: Int, id: Int) {
    }

    fun refreshCurrentFrag() {
        mRefreshListener?.refresh()
    }

    fun transact(
        frag: Fragment,
        removeBottomBar: Boolean = true,
        addToBackstack: Boolean = true
    ) {
        mTopShape = null
        mBottomShape = null

        setContainer(removeBottomBar)

        var transaction =
            supportFragmentManager.beginTransaction()
//                .setCustomAnimations(
//                R.anim.enter_from_bottom,
//                R.anim.exit_to_top,
//                R.anim.enter_from_top,
//                R.anim.exit_to_bottom)
                .replace(R.id.mainContainer, frag)

        if (addToBackstack) {
            transaction = transaction.addToBackStack(null)
        }

        transaction.commit()

        mCurrentFrag = frag
    }

    internal fun setContainer(removeBottomBar: Boolean) {
        mBinding.mainContainer.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.pageBg
            )
        )

        if (removeBottomBar) {
            if (mBinding.bottomNav.isGone) {
                mBinding.bottomNav.visibility = View.GONE
                mBinding.mainHomeContainer.visibility = View.GONE
                mBinding.mainHomeTv.visibility = View.GONE
                mBinding.mainHomeIcon.visibility = View.GONE
            }

        } else {
            if (!mBinding.bottomNav.isGone) {
                mBinding.bottomNav.visibility = View.GONE
                mBinding.mainHomeContainer.visibility = View.GONE
                mBinding.mainHomeIcon.visibility = View.GONE
            }
        }
    }

    fun showToolbar(toolbarOptions: ToolbarOptions, id: Int) {
        showToolbar(toolbarOptions, getString(id))
    }
    fun showToolbar(toolbarOptions: ToolbarOptions, title: String) {
        mBottomShape = null
        mBinding.mainToolbarTitleTv.text = title

        if (toolbarOptions.startIcon != null) {
            if (toolbarOptions.startIcon!!.icon == ToolbarOptions.Icon.NAV.icon) {
                setDrawerLock(false)
            } else {
                setDrawerLock(true)
            }

            mBinding.mainToolbarStartImg.tag = toolbarOptions.startIcon
            mBinding.mainToolbarStartImg.setImageResource(toolbarOptions.startIcon!!.icon)
            mBinding.mainToolbarStartImg.visibility = View.VISIBLE
        } else {
            mBinding.drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
            mBinding.mainToolbarStartImg.visibility = View.GONE
        }

        if (toolbarOptions.endIcon != null) {
            mBinding.mainToolbarEndImg.tag = toolbarOptions.endIcon
            mBinding.mainToolbarEndImg.setImageResource(toolbarOptions.endIcon!!.icon)
            mBinding.mainToolbarEndImg.visibility = View.VISIBLE

//            if (toolbarOptions.endIcon == ToolbarOptions.Icon.CART && App.quickInfo != null &&
//                App.quickInfo!!.cartItemsCount > 0
//            ) {
//                mBinding.mainToolbarEndCircleView.text = App.quickInfo!!.cartItemsCount.toString()
//                mBinding.mainToolbarEndCircleView.visibility = View.VISIBLE
//            } else {
//                mBinding.mainToolbarEndCircleView.visibility = View.GONE
//            }

        } else {
            mBinding.mainToolbarEndCircleView.visibility = View.GONE
            mBinding.mainToolbarEndImg.visibility = View.GONE
        }

        if (!mBinding.mainToolbar.isVisible) {
            setStatusBarColor(R.color.default_status_bar_color)
            mBinding.mainToolbar.visibility = View.VISIBLE
        }
    }

    fun hideToolbar(topShape: ViewShape? = null, bottomShape: ViewShape? = null) {
        mTopShape = topShape
        mBottomShape = bottomShape
        setDrawerLock(true)

        if (mBinding.mainToolbar.isVisible) {
            setStatusBarColor(R.color.blue_naviBar)
            mBinding.mainToolbar.visibility = View.GONE
        }
    }
    fun setDrawerLock(lock: Boolean) {
        if (lock) {
            mBinding.drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
        } else {
            mBinding.drawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED)
        }
    }

    enum class SlideMenuItem(private val type: Int) {
        HOME(0),
        CATEGORIES(1),
        OFFERZONE(2),
        CHOOSELANGUAGE(3),
        DAMAGEEXPRIY(4),
        MYCART(5),
        MYWISHLIST(6),
        NOTIFICATION(7);
        fun value(): Int {
            return type
        }
    }
}