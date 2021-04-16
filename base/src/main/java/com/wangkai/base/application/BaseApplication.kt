package com.wangkai.base.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import kotlin.properties.Delegates
open class BaseApplication(baseProcessLifecycleObserver:ProcessLifecycleObserver): Application() {

    var processLifecycleObserver = baseProcessLifecycleObserver

    companion object {
        var instance: BaseApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(processLifecycleObserver)

    }
}