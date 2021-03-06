package com.wangkai.base.application

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface ProcessLifecycleObserver: LifecycleObserver {
    /**
     * 当app变成前台进程时监听
     * 此方法一次app进入退出只会调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun enterAppListener()
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startAppListener()
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeAppListener()
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseAppListener()
    /**
     * 当app变成后台进程或者退出调用
     * 此方法一次app进入退出只会调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun exitAppListener()
}