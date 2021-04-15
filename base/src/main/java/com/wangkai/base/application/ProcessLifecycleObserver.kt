package com.wangkai.base.application

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ProcessLifecycleObserver: LifecycleObserver {
    /**
     * 当app变成前台进程时监听
     * 此方法一次app进入退出只会调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun enterAppListener(){
        Log.e("ProcessLifecycle","ON_CREATE")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startAppListener(){
        Log.e("ProcessLifecycle","ON_START")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeAppListener(){
        Log.e("ProcessLifecycle","ON_RESUME")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseAppListener(){
        Log.e("ProcessLifecycle","ON_PAUSE")
    }
    /**
     * 当app变成后台进程或者退出调用
     * 此方法一次app进入退出只会调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun exitAppListener(){
        Log.e("ProcessLifecycle","ON_STOP")
    }
}