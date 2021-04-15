package com.wangkai.base.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import kotlin.properties.Delegates

open class BaseApplication: Application() {

    companion object {
        var instance: BaseApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //添加应用生命周期观察者
        ProcessLifecycleOwner.get().lifecycle.addObserver(ProcessLifecycleObserver())

    }
}