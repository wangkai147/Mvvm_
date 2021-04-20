package com.wangkai.base.activity.state

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @Description: 带有状态管理的baseActivity
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

abstract class StateDataBindingBaseActivity<DB : ViewDataBinding> : StateBaseActivity() {
    lateinit var mDataBinding: DB

    @RequiresApi(Build.VERSION_CODES.P)
    override fun setContentView(layoutResID: Int) {
        mDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        )
        mDataBinding.lifecycleOwner = this
        super.setContentView(getLayout())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setCreateBefore()
        super.onCreate(savedInstanceState)
        setContentBefore()
        setContentView(getLayout())
        initView()
        initData()
    }

    override fun getLayout(): ViewGroup {
        return mDataBinding.root as ViewGroup
    }


}