package com.wangkai.mymusic

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.wangkai.base.activity.ViewBindingBaseActivity
import com.wangkai.base.manager.startActivity
import com.wangkai.mymusic.databinding.ActivityMainBinding

class MainActivityViewBinding : ViewBindingBaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.getNet.setOnClickListener {
//            stateViewModel.getNet()
            startActivity<StartActivity>()
        }
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