package com.wangkai.base.activity

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

abstract class BaseActivity : AppCompatActivity() {

    //封装一些用到的变量
    lateinit var mContext: Context
    lateinit var TAG: String //日志打印
    lateinit var rootView: ViewGroup //activity根布局，用于动态添加view

    //提供一些公共方法接口
    abstract fun getBundleExtras(extras: Bundle?) //接收bundle数据

    //设置状态栏导航栏颜色
    open fun setBarColor(statusBarColor: Int, navigationBarColor: Int) {
        window.statusBarColor = statusBarColor
        window.navigationBarColor = navigationBarColor
    }

    //设置app全屏
    open fun setFullScreen() {
        val controller: WindowInsetsControllerCompat? =
            ViewCompat.getWindowInsetsController(window.decorView)
        controller?.hide(WindowInsetsCompat.Type.systemBars())
    }

    //为全屏和横屏适配异形屏
    private fun adaptiveSpecialScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    //是否是横屏
    open fun isHorizontalScreen(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    private fun setPageStyle() {
        //状态栏相关的
        adaptiveSpecialScreen()
        setBarColor(Color.TRANSPARENT, Color.TRANSPARENT)
        //状态栏字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

//        //代码使布局延伸到状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.setDecorFitsSystemWindows(false)
//            //稳定布局，防止闪烁
//            val controller: WindowInsetsControllerCompat? =
//                ViewCompat.getWindowInsetsController(window.decorView)
//            controller?.systemBarsBehavior
//            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//
//        } else {
//            @Suppress("DEPRECATION")
//            window.decorView.systemUiVisibility = (
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    )
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        TAG = mContext.javaClass.simpleName
        intent.extras?.let { getBundleExtras(it) }

        //初始化根布局
        rootView = this.window.decorView.findViewById(android.R.id.content)
        //设置状态栏并适配异性屏幕
        setPageStyle()
    }
}