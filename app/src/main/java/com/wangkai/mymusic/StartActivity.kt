package com.wangkai.mymusic

/**
 * @Description: 启动页
 * @Author: JWangZzz
 * @CreateDate: 2021.4.16
 */

import android.os.Bundle
import com.wangkai.base.activity.ViewBindingBaseActivity
import com.wangkai.mymusic.databinding.ActivityStartBinding
import com.wangkai.base.manager.startActivity

class StartActivity : ViewBindingBaseActivity<ActivityStartBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding.animationView.playAnimation()
        setFullScreen()
        initData()
    }


    override fun getBundleExtras(extras: Bundle?) {

    }

    fun initData() {
        //开屏动画
        val list = mutableListOf(
            "pride-hard-seltzer.json",
            "lamsa-splash-screen.json",
            "ramadan-kareem-hello-doc.json",
            "logo-animation.json"
        )
        mViewBinding.animationView.addAnimatorUpdateListener {
            if (it.animatedFraction == 1f) {
                startActivity<MainActivityViewBinding>()
            }
        }
        mViewBinding.animationView.setAnimation(list.random())
    }
}