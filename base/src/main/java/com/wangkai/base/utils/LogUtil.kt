package com.wangkai.base.utils

import android.util.Log
import com.wangkai.base.BuildConfig

object LogUtil {
    private var isDebug =  BuildConfig.DEBUG

    fun d(tag:String,msg:String){
        if (isDebug) {
            Log.d(tag,msg)
        }
    }

    fun e(tag:String,msg:String){
        if (isDebug) {
            Log.e(tag,msg)
        }
    }
    fun i(tag:String,msg:String){
        if (isDebug) {
            Log.i(tag,msg)
        }
    }
    fun v(tag:String,msg:String){
        if (isDebug) {
            Log.v(tag,msg)
        }
    }
    fun w(tag:String,msg:String){
        if (isDebug) {
            Log.w(tag,msg)
        }
    }
    fun onSuccess(tag:String,msg:String){
        if (isDebug) {
            Log.e("onSuccess", "$tag:$msg")
        }
    }


}