package com.wangkai.base.activity.state

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wangkai.base.R
import com.wangkai.base.activity.BaseActivity
import com.wangkai.base.status.EState
import com.wangkai.base.status.StateViewModel
import com.wangkai.base.utils.LogUtil
import com.wangkai.base.utils.ToastUtil

/**
 * @Description: 带有状态管理的baseActivity
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

abstract class StateBaseActivity : BaseActivity() {


    //网络状态页面
    private lateinit var vsLoading: ViewStub
    private lateinit var vsError: ViewStub
    private lateinit var inflaterLoading: View
    private lateinit var inflaterError: View

    //管理页面状态的viewModel
    val stateViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            StateViewModel::class.java
        )
    }

    abstract fun addLoadingLayout(): ViewGroup
    abstract fun addErrorLayout(): ViewGroup
    abstract fun getLayout(): ViewGroup


    //初始化状态的布局
    private fun initStatusView() {

        //加载成功的布局
        vsLoading = ViewStub(this, R.layout.common_base_loading)
        rootView.addView(vsLoading)

        //加载失败的布局
        vsError = ViewStub(this, R.layout.common_base_error)
        rootView.addView(vsError)
    }

    fun initStatusViewModel() {
        stateViewModel.stateLiveData.observe(this, Observer { t ->
            t?.let {
                when (t) {
                    EState.Loading -> {
                        getLayout().addView(addLoadingLayout())
                        LogUtil.e("AAAAAAAAAAAA","加载中")
                    }
                    EState.Error -> {
                        getLayout().addView(addErrorLayout())
                        LogUtil.e("AAAAAAAAAAAA","加载失败")
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
}