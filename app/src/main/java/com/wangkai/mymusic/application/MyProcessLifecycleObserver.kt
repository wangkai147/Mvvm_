package com.wangkai.mymusic.application

import com.wangkai.base.application.ProcessLifecycleObserver
import com.wangkai.base.utils.LogUtil

/**
 * @Description: java类作用描述
 * @Author: JWangZzz
 * @CreateDate: 2021/4/16 23:54
 */
class MyProcessLifecycleObserver: ProcessLifecycleObserver {
    override fun enterAppListener() {
        LogUtil.e("MyProcessLifecycleObserver","enterAppListener")
    }

    override fun startAppListener() {
        LogUtil.e("MyProcessLifecycleObserver","startAppListener")
    }

    override fun resumeAppListener() {
        LogUtil.e("MyProcessLifecycleObserver","resumeAppListener")
    }

    override fun pauseAppListener() {
        LogUtil.e("MyProcessLifecycleObserver","pauseAppListener")
    }

    override fun exitAppListener() {
        LogUtil.e("MyProcessLifecycleObserver","exitAppListener")
    }

}