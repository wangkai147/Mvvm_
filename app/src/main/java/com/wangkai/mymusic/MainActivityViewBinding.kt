package com.wangkai.mymusic

import android.os.Bundle
import android.view.ViewGroup
import com.wangkai.base.activity.demo.StateViewBindingBaseActivity
import com.wangkai.base.state.StateLayoutManager
import com.wangkai.base.utils.LogUtil
import com.wangkai.mymusic.databinding.ActivityMainBinding

class MainActivityViewBinding : StateViewBindingBaseActivity<ActivityMainBinding>() {

    override fun initStatusLayout() {
        statusLayoutManager = StateLayoutManager.newBuilder(this)
            .contentView(getLayoutId())
            .build()
//
//        mViewBinding.getNet.setOnClickListener {
//            LogUtil.e("AAAAAAAAAAAA","点击了")
//
//        }
    }

    override fun getBundleExtras(extras: Bundle?) {

    }
}