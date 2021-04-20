package com.wangkai.mymusic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.wangkai.base.activity.state.StateViewBindingBaseActivity
import com.wangkai.base.utils.LogUtil
import com.wangkai.mymusic.databinding.ActivityMainBinding

class MainActivityViewBinding : StateViewBindingBaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusViewModel()
        mViewBinding.getNet.setOnClickListener {
            stateViewModel.getNet()
            LogUtil.e("AAAAAAAAAAAA","点击了")
        }
    }

    override fun addLoadingLayout(): ViewGroup {
        return LayoutInflater.from(mContext)
            .inflate(R.layout.common_base_loading, null) as ViewGroup
    }

    override fun addErrorLayout(): ViewGroup {
        return LayoutInflater.from(mContext)
            .inflate(R.layout.common_base_error, null) as ViewGroup
    }

    override fun setCreateBefore() {
    }

    override fun setContentBefore() {
    }

    override fun getBundleExtras(extras: Bundle?) {

    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun initViewModel() {

    }
}