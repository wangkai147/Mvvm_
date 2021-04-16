package com.wangkai.base.activity

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.wangkai.base.R
import com.wangkai.base.status.EState
import com.wangkai.base.status.StateViewModel

abstract class BaseActivity : AppCompatActivity() {

    //封装一些用到的变量
    lateinit var mContext: Context
    lateinit var TAG: String

    //提供一些公共方法接口
    abstract fun setCreateBefore() //setCreateBefore之前的方法
    abstract fun setContentBefore() //setContentView之前的方法
    abstract fun getBundleExtras(extras: Bundle?) //接收bundle数据

    abstract fun initData()
    abstract fun initView()
    abstract fun initViewModel() //初始化ViewModel



    //网络状态页面
    private lateinit var vsLoading: ViewStub
    private lateinit var vsError: ViewStub
    private lateinit var inflaterLoading: View
    private lateinit var inflaterError: View

    //activity根布局，用于动态添加view
    private lateinit var rootView: ViewGroup

    //管理页面状态的viewModel
    lateinit var stateViewModel: StateViewModel

    fun initStatusViewModel() {
        //初始化状态的viewModel
        stateViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                StateViewModel::class.java
            )
        stateViewModel.stateLiveData.observe(this, { t ->
            t?.let {
                when (t) {
                    EState.Loading -> {
                        showLoadingLayout(true)
                    }
                    EState.Error -> {
                        showErrorLayout(true)
                    }
                    EState.None -> {
                    }
                    EState.Empty -> {
                    }
                    EState.Success -> {
                    }
                }
            }
        })
    }


    //显示加载中界面
    open fun showLoadingLayout(boolean: Boolean) {
        if (boolean) {
            try {
                inflaterLoading = vsLoading.inflate()
            } catch (e: Exception) {
                if (!::vsLoading.isInitialized) {
                    initStatusView()
                    inflaterLoading = vsLoading.inflate()
                }
            } finally {
                inflaterLoading.visibility = View.VISIBLE
            }

        } else {
            try {
                inflaterLoading.visibility = View.INVISIBLE
            } catch (e: Exception) {

            }
        }
    }

    //显示加载错误界面
    open fun showErrorLayout(boolean: Boolean) {
        if (boolean) {
            try {
                inflaterError = vsError.inflate()
            } catch (e: Exception) {
                if (!::vsError.isInitialized) {
                    initStatusView()
                    inflaterError = vsError.inflate()
                }
            } finally {
                inflaterError.visibility = View.VISIBLE
            }
        } else {
            try {
                inflaterError.visibility = View.INVISIBLE
            } catch (e: Exception) {

            }
        }
    }


    //初始化状态的布局
    private fun initStatusView() {

        //加载成功的布局
        vsLoading = ViewStub(this, R.layout.common_base_loading)
        rootView.addView(vsLoading)
        //加载失败的布局
        vsError = ViewStub(this, R.layout.common_base_error)
        rootView.addView(vsError)
    }




    //设置状态栏导航栏颜色
    open fun setBarColor(statusBarColor: Int, navigationBarColor: Int) {
        window.statusBarColor = statusBarColor
        window.navigationBarColor = navigationBarColor
    }

    //app全屏
    open fun setFullScreen() {
        val controller: WindowInsetsControllerCompat? =
            ViewCompat.getWindowInsetsController(window.decorView)
        controller?.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun setPageStyle(){
        //WindowInsets目前还在测试版，故采用已废弃方法 -- 状态栏字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setBarColor(Color.TRANSPARENT, Color.TRANSPARENT)
        rootView.fitsSystemWindows = false
        //使布局延伸到状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            //稳定布局，防止闪烁
            val controller: WindowInsetsControllerCompat? =
                ViewCompat.getWindowInsetsController(window.decorView)
            controller?.systemBarsBehavior
            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            //适配异形屏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        TAG = mContext.javaClass.simpleName
        intent.extras?.let { getBundleExtras(it) }

        super.onCreate(savedInstanceState)
        //初始化根布局
        rootView = this.window.decorView.findViewById(android.R.id.content)
        //设置状态栏并适配异性屏幕
        setPageStyle()
    }


}