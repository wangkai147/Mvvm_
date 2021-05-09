package com.wangkai.base.activity.demo

import android.os.Bundle
import android.view.ViewGroup
import com.wangkai.base.activity.BaseActivity
import com.wangkai.base.state.StateLayoutManager


/**
 * @Description: 带有状态管理的baseActivity
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

abstract class StateBaseActivity : BaseActivity() {
    //页面状态管理器
    protected lateinit var statusLayoutManager: StateLayoutManager

    abstract fun initData()
    abstract fun initView()

    abstract fun getLayout(): ViewGroup//获取根布局
    abstract fun getLayoutId(): Int//获取根布局Id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //正常展示数据状态
    protected open fun showContent() = statusLayoutManager.showContent()

    //加载数据为空时状态
    protected open fun showEmptyData() = statusLayoutManager.showEmptyData()

    //加载数据错误时状态
    protected open fun showError() = statusLayoutManager.showError()

    //网络错误时状态
    protected open fun showNetWorkError() = statusLayoutManager.showNetWorkError()

    //正在加载中状态
    protected open fun showLoading() = statusLayoutManager.showLoading()
}