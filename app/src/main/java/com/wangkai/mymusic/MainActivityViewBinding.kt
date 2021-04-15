package com.wangkai.mymusic

import android.os.Bundle
import android.view.View
import com.wangkai.base.activity.ViewBindingBaseActivity
import com.wangkai.mymusic.databinding.ActivityMainBinding

class MainActivityViewBinding : ViewBindingBaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.getNet.setOnClickListener(View.OnClickListener {
            stateViewModel.getNet()
        })
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