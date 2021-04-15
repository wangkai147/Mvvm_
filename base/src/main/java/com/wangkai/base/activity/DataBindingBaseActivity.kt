package com.wangkai.base.activity

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wangkai.base.R
import com.wangkai.base.utils.LogUtil


abstract class DataBindingBaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var mDataBinding: DB

    lateinit var mContext: Context
    lateinit var TAG: String


    private lateinit var rootView: ViewGroup
    private lateinit var vsLoading: ViewStub
    private lateinit var inflaterLoading: View
    private lateinit var vsError: ViewStub
    private lateinit var inflaterError: View

    abstract fun setCreateBefore() //setCreateBefore之前的方法
    abstract fun setContentBefore() //setContentView之前的方法
    abstract fun getBundleExtras(extras: Bundle?) //接收bundle数据

    abstract fun initData()
    abstract fun initView()

    abstract fun initViewModel() //初始化ViewModel

    //设置状态栏导航栏颜色
    open fun setBarColor(statusBarColor: Int, navigationBarColor: Int) {
        window.statusBarColor = statusBarColor
        window.navigationBarColor = navigationBarColor
        //WindowInsets目前还在测试版，故采用已废弃方法 -- 状态栏字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
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
                    LogUtil.e("showLoading", "界面不存在需要添加")
                } else {
                    LogUtil.e("showLoading", "界面存在不需要添加")
                }
            } finally {
                inflaterLoading.visibility = View.VISIBLE
            }

        } else {
            try {
                inflaterLoading.visibility = View.INVISIBLE
                LogUtil.e("showLoading", "界面存在，隐藏")
            } catch (e: Exception) {
                LogUtil.e("showLoading", "界面没有不需要隐藏")
            }
        }
    }

    private fun initStatusView() {
        rootView = this.window.decorView.findViewById(android.R.id.content)

        //加载成功的布局
        vsLoading = ViewStub(this, R.layout.common_base_loading)
        rootView.addView(vsLoading)

        //加载失败的布局
        vsError = ViewStub(this, R.layout.common_base_error)
        rootView.addView(vsError)

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun setContentView(layoutResID: Int) {
        mDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        );
        mDataBinding.lifecycleOwner = this


        super.setContentView(mDataBinding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setCreateBefore()
        super.onCreate(savedInstanceState)
        mContext = this
        TAG = mContext.javaClass.simpleName
        setContentBefore()
        setContentView(mDataBinding.root)

        intent.extras?.let { getBundleExtras(it) }

        initView()
        initData()
        setBarColor(Color.TRANSPARENT, Color.TRANSPARENT)
    }



}